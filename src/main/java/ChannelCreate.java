import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.EnumSet;
import java.util.function.Consumer;

public class ChannelCreate {


    public void createSupportTicket(MessageReceivedEvent event) {

        Main.ticketCount++;

        Main.save("ticket-count", Main.ticketCount + "");

        event.getMessage().delete().queue();

        EnumSet<Permission> roleAllow = EnumSet.noneOf(Permission.class);
        EnumSet<Permission> roleDeny = EnumSet.of(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE);

        EnumSet<Permission> memberAllow = EnumSet.of(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE);
        EnumSet<Permission> memberDeny = EnumSet.of(Permission.ADMINISTRATOR);

        event.getGuild().getController().createTextChannel(event.getMember().getEffectiveName() + " Ticket " + Main.ticketCount)
                .addPermissionOverride(event.getMember(), memberAllow, memberDeny)
                .addPermissionOverride(event.getGuild().getPublicRole(), roleAllow, roleDeny)
                .queue(new Consumer<Channel>() {
                    @Override
                    public void accept(Channel channel) {
                        if(channel instanceof TextChannel) {

                            EmbedBuilder embedBuilder = new EmbedBuilder();

                            embedBuilder.setTitle(":ballot_box_with_check: Support Ticket :ballot_box_with_check:");
                            embedBuilder.setAuthor(event.getMember().getEffectiveName());
                            embedBuilder.setDescription("Support ticket created successfully " + event.getMember().getAsMention());

                            embedBuilder.setColor(Color.green);

                            ((TextChannel) channel).sendMessage(embedBuilder.build()).queue();

                        }
                    }
                });

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle(":ballot_box_with_check: Support Ticket :ballot_box_with_check: ");
        embedBuilder.setAuthor(event.getMember().getEffectiveName());
        embedBuilder.setDescription("Support ticket created successfully!");

        embedBuilder.setColor(Color.orange);

        event.getChannel().sendMessage(embedBuilder.build()).queue();

    }

}
