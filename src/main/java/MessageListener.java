import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MessageListener extends ListenerAdapter {

    private ChannelCreate channelCreate = new ChannelCreate();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if(message.startsWith("!createticket")) {
            if(event.getChannel().getName().equalsIgnoreCase("support-create")) {
                channelCreate.createSupportTicket(event);
            }
        }

        if(message.startsWith("!approve")) {

            if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {

                String[] args = event.getChannel().getName().split("-");

                if(args[1].equalsIgnoreCase("ticket")) {

                    event.getMessage().delete().queue();

                    String channelId = event.getChannel().getId();

                    EmbedBuilder embedBuilder = new EmbedBuilder();

                    embedBuilder.setTitle(":ballot_box_with_check: Request Approved :ballot_box_with_check: ");
                    embedBuilder.setDescription("This request has been approved by " + event.getMember().getAsMention() + "\n \nTicket closing in 10 minutes.");

                    embedBuilder.setColor(Color.green);

                    event.getChannel().sendMessage(embedBuilder.build()).queue();

                    event.getGuild().getTextChannelById(channelId).delete().queueAfter(10, TimeUnit.MINUTES);

                }
            }
        }

        if(message.startsWith("!deny")) {

            if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {

                String[] args = event.getChannel().getName().split("-");

                if(args[1].equalsIgnoreCase("ticket")) {
                    event.getMessage().delete().queue();

                    String channelId = event.getChannel().getId();

                    EmbedBuilder embedBuilder = new EmbedBuilder();

                    embedBuilder.setTitle(":x: Request Denied :x:");
                    embedBuilder.setDescription("This request has been denied by " + event.getMember().getAsMention() + "\n \nTicket closing in 10 minutes.");

                    embedBuilder.setColor(Color.red);

                    event.getChannel().sendMessage(embedBuilder.build()).queue();

                    event.getGuild().getTextChannelById(channelId).delete().queueAfter(10, TimeUnit.MINUTES);

                }
            }
        }
    }


}
