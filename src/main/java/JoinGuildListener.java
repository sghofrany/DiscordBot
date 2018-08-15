import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JoinGuildListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getMember().getGuild().getController().addRolesToMember(event.getMember(), event.getJDA().getRolesByName("Default", true)).queue();
    }

}
