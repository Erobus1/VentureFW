package io.github.erobus1.bot.commands.util;

public class CommandInfo {
    private final String name;
    private final String description;
    private final String usage;
    private final String category;
    private final String[] aliases;
    private final Permission permission;


    public CommandInfo(String name, String description, String usage, String category, String[] aliases, Permission permission) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.category = category;
        this.aliases = aliases;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public String getCategory() {
        return category;
    }

    public String[] getAliases() {
        return aliases;
    }

    public Permission getPermission() {
        return permission;
    }
}
