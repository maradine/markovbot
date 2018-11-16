import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

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

        //command processing

        String command = event.getMessage().getContentStripped();
        String commandLower = command.toLowerCase();

        Scanner scanner = new Scanner(commandLower);
        String token = scanner.next();

        if (token.equals("!markov")) {

            if (!scanner.hasNext()) {
                event.getTextChannel().sendMessage("Hello markov.").queue();
            } else {
                switch (scanner.next()) {
                    case "me":
                        event.getTextChannel().sendMessage("Gonna markov you when I learn how.").queue();
                        break;

                    case "stats":
                        event.getTextChannel().sendMessage("Markov cloud knows " + cloud.size() + " chains.").queue();
                        long id = event.getAuthor().getIdLong();
                        if (cloud.containsKey(id) {
                            long chains = cloud.get(id).size();
                            event.getTextChannel().sendMessage("Your chain knows " + chains + " roots.").queue();
                        } else {
                            event.getTextChannel().sendMessage("You don't have a chain in the cloud.");
                        }


                        break;
                }
            }
            return;
        }


        // Markov ingestion logic
        long id = event.getAuthor().getIdLong();
        String messageContent = event.getMessage().getContentStripped();
        System.out.println("ingesting message from id " + id);
        cloud.ingest(id, messageContent);


    }


}
