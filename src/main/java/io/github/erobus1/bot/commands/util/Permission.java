package me.erobus.bot.commands.util;

import me.erobus.Venture;
import net.dv8tion.jda.api.entities.Member;

import java.util.function.Function;

public enum Permission {
    NONE("none", member -> true),
    MODERATOR("moderator", member -> member.hasPermission(net.dv8tion.jda.api.Permission.BAN_MEMBERS)),
    GUILD_ADMIN("administrator", member -> member.hasPermission(net.dv8tion.jda.api.Permission.ADMINISTRATOR)),
    GUILD_OWNER("owner", Member::isOwner),
    DEVELOPER("developer", member -> Venture.getDevelopers().contains(member.getId()))
    ;


    private final String name;
    private final Function<Member, Boolean> callback;

    Permission(String name, Function<Member, Boolean> callback) {
        this.name = name;
        this.callback = callback;
    }

    public String getName() {
        return name;
    }

    public Function<Member, Boolean> getCallback() {
        return callback;
    }
}
