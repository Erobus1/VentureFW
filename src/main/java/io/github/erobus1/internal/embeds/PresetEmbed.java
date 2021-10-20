package me.erobus.internal.embeds;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public interface PresetEmbed {
    EmbedBuilder SUCCESS = new EmbedBuilder()
            .setAuthor("Success")
            .setColor(Color.GREEN);
    EmbedBuilder WARNING = new EmbedBuilder()
            .setAuthor("Warning")
            .setColor(Color.ORANGE);
    EmbedBuilder ERROR = new EmbedBuilder()
            .setAuthor("Error")
            .setColor(Color.RED);
    EmbedBuilder DEFAULT = new EmbedBuilder()
            .setColor(0x2f3136);
}
