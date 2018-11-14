import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MarkovBot extends ListenerAdapter {

    private static long botdevChannelID = 497662058481844224L;
    private static Properties props = null;
    private static MarkovCloud cloud = null;

    public static void main(String[] args) throws LoginException {

        try {
            FileInputStream fis = new FileInputStream("markovbot.properties");
            props = new Properties();
            props.load(fis);
        } catch (IOException ioe) {
            System.out.println("Failed to load properties from file - ioexception");
            System.out.println(ioe.toString());
            System.exit(1);
        }

        String token = props.getProperty("botToken");
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.addEventListener(new MarkovBot());
        builder.build();

        cloud = new MarkovCloud();

        System.out.println("Instantiation complete.");

    }




    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //ignore ourselves (and other bots)
        if (event.getAuthor().isBot()) {
            return;
        }

        // any other exits or exclusions

        // Markov ingestion logic
        long id = event.getAuthor().getIdLong();
        String messageContent = event.getMessage().getContentStripped();
        System.out.println("ingesting message from id " + id);
        cloud.ingest(id, messageContent);


    }


}
