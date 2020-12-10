import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault(BotToken.getToken()).build();
        jda.getPresence().setActivity(Activity.listening("Character names"));
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.addEventListener(new Main());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()){
            System.out.println("We have received a message from "
                    + event.getAuthor().getName() + " (id:"
                    + event.getAuthor().getId() + ") : "
                    + event.getMessage().getContentDisplay()
            );
            // use raw so we have the content exactly as the user sent it
            if (event.getMessage().getContentRaw().equals("f.ping!")) {
                // remember to call queue()! otherwise the message will never be sent!
                event.getChannel().sendMessage("Pong!").queue();
            }
        } else {
            return;
        }


    }


}
