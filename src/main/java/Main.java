import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class Main {

    private static JDA jda;

    public static Properties properties = new Properties();

    public static int ticketCount = 0;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {

        jda = new JDABuilder(AccountType.BOT).setToken("NDc4MjQwMjQyNzU1OTYwODMy.DlH0Eg.V19ybOjfpExyJ7h2XKOcQ5PFLbI").buildBlocking();

        jda.addEventListener(new MessageListener());
        jda.addEventListener(new JoinGuildListener());

        ticketCount = Integer.parseInt(load("ticket-count"));

        jda.getSelfUser().getManager().setName("PvPTemple").queue();

    }

    public static void save(String key, String value) {
        try {
            properties.setProperty(key, value);
            properties.store(new FileOutputStream("config"), null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String load(String key) {

        String value = "";

        try {
            properties.load(new FileInputStream("config"));
            value = properties.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


}
