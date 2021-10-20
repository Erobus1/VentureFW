package me.erobus.bot.commands.util;

public class Usage {
    private int minArgs = 0;
    private final String display;

    public Usage(String usage) {
        display = usage;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public Usage setMinArgs(int minArgs) {
        this.minArgs = minArgs;
        return this;
    }

    public String getDisplay() {
        return display;
    }
}
