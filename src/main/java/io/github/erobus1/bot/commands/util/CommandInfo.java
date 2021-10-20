package me.erobus.bot.commands.util;

public record CommandInfo(String name, String description, String usage, String category, String[] aliases, Permission permission) {
}
