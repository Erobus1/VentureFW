package io.github.erobus1.bot.commands;

import io.github.erobus1.Venture;
import io.github.erobus1.bot.commands.util.CommandHelper;
import io.github.erobus1.bot.commands.util.Permission;
import io.github.erobus1.internal.Logger;
import io.github.erobus1.internal.Note;
import io.github.erobus1.internal.embeds.DefaultEmbeds;
import io.github.erobus1.internal.exceptions.InvalidUsageException;
import io.github.erobus1.internal.exceptions.MissingAnnotationException;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {
    private final HashMap<String, MessageCommand> COMMANDS = new HashMap<>();
    private final List<String> CATEGORIES = new ArrayList<>();

    public CommandHandler() throws MissingAnnotationException, InvalidUsageException {
        for (MessageCommand cmd : Venture.getCommands()) {
            Class<? extends MessageCommand> cmdClass = cmd.getClass();
            if (!cmdClass.isAnnotationPresent(Command.class)) throw new MissingAnnotationException("Missing command annotation! At " + cmd.getClass().getName());
            Command cmdInfo = cmdClass.getAnnotation(Command.class);
            String name = cmdInfo.name().toLowerCase();
            String[] aliases = cmdInfo.aliases();
            COMMANDS.put(name, cmd);
            CommandHelper.parseUsage(cmd);
            if (!CATEGORIES.contains(cmdInfo.category())) CATEGORIES.add(cmdInfo.category().toLowerCase().substring(0, 1).toUpperCase() + cmdInfo.category().toLowerCase().substring(1));
            System.out.println(Logger.GREEN(
                    "Registering \"" + name + "\" command. Class: " + cmdClass.getName()
            ));
            if (cmdClass.isAnnotationPresent(Note.class)) {
                System.out.println(Logger.CYAN(
                        " --> " + cmdClass.getAnnotation(Note.class).content()
                ));
            }
            for (String alias : aliases) COMMANDS.put(alias.toLowerCase(), cmd);
        }
    }

    public void runCommand(CommandEvent event) {
        MessageCommand command = COMMANDS.get(event.getCommandName().toLowerCase());
        if (command == null) return;
        Command commandInfo = CommandHelper.getInfoAnnotation(command);
        event.setCommand(command);

        EmbedBuilder errorEmbed = DefaultEmbeds.ERROR();


        if (commandInfo.permission() != Permission.NONE) {
            for (int i = 0; i < Permission.values().length; i++) {
                if (!Permission.values()[i].getCallback().apply(event.getMember()) && commandInfo.permission().ordinal() >= i) {
                    errorEmbed.setTitle("Insufficient permission!")
                            .setDescription("This command requires at least the `" + commandInfo.permission().getName() + "` permission to be executed!");
                    event.reply(errorEmbed);
                    return;
                }
            }
        }

        command.execute(event);
    }

    public List<String> getCategories() {
        return CATEGORIES;
    }

    public HashMap<String, MessageCommand> getCommands() {
        return COMMANDS;
    }

    public MessageCommand getCommand(String commandName) {
        return COMMANDS.get(commandName);
    }
}
