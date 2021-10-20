package io.github.erobus1;

import io.github.erobus1.bot.commands.MessageCommand;
import io.github.erobus1.bot.commands.impl.HelpCommand;
import io.github.erobus1.bot.defaultlisteners.CommandListener;
import io.github.erobus1.internal.Logger;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class to build the Venture class.
 */
public class VentureBuilder {
    private final List<String> DEVELOPERS;
    private String TOKEN;
    private String PREFIX;
    private String BOT_INFO = "This is a bot created with the Venture framework";
    private final List<ListenerAdapter> EVENT_LISTENERS = new ArrayList<>();
    private final List<MessageCommand> COMMANDS = new ArrayList<>();
    private static boolean helpCommand = true;

    private VentureBuilder(String token, String prefix, String... developers) {
        TOKEN = token;
        DEVELOPERS = developers.length == 1 && developers[0].equals("") ? new ArrayList<>() : Arrays.asList(developers);
        PREFIX = prefix;
        EVENT_LISTENERS.add(new CommandListener());
    }

    public static VentureBuilder createDefault() {
        return createDefault(null);
    }

    public static VentureBuilder createDefault(String token) {
        return createDefault(token, null);
    }

    public static VentureBuilder createDefault(String token, String prefix) {
        return createDefault(token, prefix, "");
    }

    public static VentureBuilder createDefault(String token, String prefix, String... developers) {
        return new VentureBuilder(token, prefix, developers);
    }

    public VentureBuilder setToken(String token) {
        TOKEN = token;
        return this;
    }

    public VentureBuilder addDevelopers(String... developers) {
        DEVELOPERS.addAll(Arrays.asList(developers));
        return this;
    }

    public VentureBuilder setPrefix(String prefix) {
        PREFIX = prefix;
        return this;
    }

    public VentureBuilder addListeners(ListenerAdapter... listeners) {
        EVENT_LISTENERS.addAll(Arrays.asList(listeners));
        return this;
    }

    public VentureBuilder addCommands(MessageCommand... commands) {
        COMMANDS.addAll(Arrays.asList(commands));
        return this;
    }

    public VentureBuilder noHelp() {
        helpCommand = false;
        return this;
    }

    /**
     *
     * @param info The message that will be displayed by using the default help command or can be retrieved by using {@link Venture#getBotInfo}
     */
    public VentureBuilder setBotInfo(String info) {
        BOT_INFO = info;
        return this;
    }

    public void build() {

        if (PREFIX == null) {
            PREFIX = "!";
            System.out.println(Logger.RED(
                    "Warning: No prefix was specified! Using the default prefix (!) instead"
            ));
        }

        if (COMMANDS.size() == 0) {
            System.out.println(Logger.RED(
                    "Warning: Are you sure you don't want to add any commands?"
            ));
        }

        if (helpCommand) addCommands(new HelpCommand());

        if (DEVELOPERS.size() == 0) {
            System.out.println(Logger.RED(
                    "Warning: No developer ids have been added to the bot."
            ));
        }

        if (EVENT_LISTENERS.size() == 0) {
            System.out.println(Logger.CYAN(
                    "Info: No additional listeners were added!"
            ));
        }

        Venture.create(TOKEN, DEVELOPERS, PREFIX, EVENT_LISTENERS.toArray(ListenerAdapter[]::new), COMMANDS.toArray(MessageCommand[]::new), BOT_INFO);
    }
}
