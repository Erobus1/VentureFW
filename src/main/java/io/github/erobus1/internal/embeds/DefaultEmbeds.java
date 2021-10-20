package io.github.erobus1.internal.embeds;

import net.dv8tion.jda.api.EmbedBuilder;

public class DefaultEmbeds implements PresetEmbed{
    public static EmbedBuilder SUCCESS() {
        EmbedBuilder b = new EmbedBuilder();
        b.copyFrom(SUCCESS);
        return b;
    }

    public static EmbedBuilder WARNING() {
        EmbedBuilder b = new EmbedBuilder();
        b.copyFrom(WARNING);
        return b;
    }

    public static EmbedBuilder ERROR() {
        EmbedBuilder b = new EmbedBuilder();
        b.copyFrom(ERROR);
        return b;
    }

    public static EmbedBuilder DEFAULT() {
        EmbedBuilder b = new EmbedBuilder();
        b.copyFrom(DEFAULT);
        return b;
    }
}
