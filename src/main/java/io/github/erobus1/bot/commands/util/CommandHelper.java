package me.erobus.bot.commands.util;

import me.erobus.bot.commands.MessageCommand;
import me.erobus.bot.commands.Command;
import me.erobus.internal.exceptions.InvalidUsageException;

public class CommandHelper {

    private static final String openErr = "Can not open a second argument before closing!";
    private static final String closeErr = "Can not close an argument without opening one!";

    public static Usage parseUsage(MessageCommand command) throws InvalidUsageException {
        String usage = command.getClass().getAnnotation(Command.class).usage();
        Usage parsed = new Usage(usage);
        if (usage.equals("")) return parsed;
        boolean open = false;
        boolean lastReq = true;
        int totalReqArgs = 0;
        char[] usageChars = usage.toCharArray();
        for (char uChar : usageChars) {
            if (uChar == '<') {
                if (open) throw new InvalidUsageException(openErr);
                if (!lastReq) throw new InvalidUsageException("Can not require an argument after an optional argument!");

                open = true;
                totalReqArgs++;
            }

            if (uChar == '>') {
                if (open) open = false;
                else throw new InvalidUsageException(closeErr);
            }

            if (uChar == '[') {
                if (open) throw new InvalidUsageException(openErr);
                open = true;
                lastReq = false;
            }

            if (uChar == ']') {
                if (open) open = false;
                else throw new InvalidUsageException(closeErr);
            }
        }

        if (open) throw new InvalidUsageException("Can not leave an argument open without closing it!");
        parsed.setMinArgs(totalReqArgs);
        return parsed;
    }

    public static CommandInfo getInfo(MessageCommand command) {
        Command cmdInfo = command.getClass().getAnnotation(Command.class);
        String name = cmdInfo.name();
        String description = cmdInfo.description();
        String usage = cmdInfo.usage();
        String category = cmdInfo.category();
        String[] aliases = cmdInfo.aliases();
        Permission permission = cmdInfo.permission();

        return new CommandInfo(name, description, usage, category, aliases, permission);
    }

    public static Command getInfoAnnotation(MessageCommand command) {
        return command.getClass().getAnnotation(Command.class);
    }
}
