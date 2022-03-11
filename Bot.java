
import com.sun.tools.javac.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Bot {

    private final ScheduledExecutorService execeutorService;
    private static JDA jda;

    public Bot() throws InterruptedException {
        execeutorService = Executors.newScheduledThreadPool(10);
        jda = createUser();
        if (jda == null) return;
    }

    /**
     * Method to create the bot connection
     * @return returns the JDA class which is the creation of the bot
     */
    public static JDA getJda() {
        return jda;
    }
    JDA createUser(){
        final String TOKEN = "OTUxNjcxODQ1NjEzMTA5MzA4.Yiq3eQ.x4HyYEhje6o1i18DGc0i0uPkBHE";
        JDABuilder jda = JDABuilder.createDefault(TOKEN);
        //Event Listeners defined for the bot
        jda.addEventListeners(
                new Driver(),
                new BotCommands());
        //Sets the bot activity
        jda.setActivity(Activity.watching("Orders"));
        //Sets the status of the bot
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        jda.enableCache(CacheFlag.ACTIVITY);
        try{
            return jda.build();
        }catch(LoginException e){
            e.printStackTrace();
            return this.jda;
        }
    }

    public ScheduledExecutorService getExeceutorService() {
        return execeutorService;
    }
}
