package me.erobus;

import me.erobus.bot.commands.CommandHandler;
import me.erobus.bot.commands.MessageCommand;
import me.erobus.internal.exceptions.InvalidUsageException;
import me.erobus.internal.exceptions.MissingAnnotationException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.List;

public class Venture {
    private static String TOKEN;
    private static List<String> DEVELOPERS;
    private static String PREFIX;
    private static String BOT_INFO;
    private static ListenerAdapter[] LISTENERS;
    private static MessageCommand[] COMMANDS;
    private static JDA jda;
    private static CommandHandler HANDLER;

    public static void create(String token, List<String> developers, String prefix, ListenerAdapter[] listeners, MessageCommand[] commands, String botInfo) {
        TOKEN = token;
        DEVELOPERS = developers;
        PREFIX = prefix;
        LISTENERS = listeners;
        COMMANDS = commands;
        BOT_INFO = botInfo;
    }

    public static void initialize() throws LoginException, MissingAnnotationException, InvalidUsageException {
        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        Object[] listeners = LISTENERS;
        builder.addEventListeners(listeners);
        jda = builder.build();

        HANDLER = new CommandHandler();
    }

    public static List<String> getDevelopers() {
        return DEVELOPERS;
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public static JDA getJda() {
        return jda;
    }

    public static MessageCommand[] getCommands() {
        return COMMANDS;
    }

    public static CommandHandler getCmdHandler() {
        return HANDLER;
    }

    /**
     * Use this method to get basic information about the bot.
     *
     * @return The bot information, given when building the client.
     * @see VentureBuilder#setBotInfo(String)
     */
    public static String getBotInfo() {
        return BOT_INFO;
    }
}
