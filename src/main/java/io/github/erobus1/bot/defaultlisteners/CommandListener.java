package me.erobus.bot.defaultlisteners;

import me.erobus.Venture;
import me.erobus.bot.commands.CommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) return;

        String content = event.getMessage().getContentRaw();
        if (!content.startsWith(Venture.getPrefix())) return;
        String commandName = content.split("\\s+")[0].substring(Venture.getPrefix().length()).toLowerCase();
        List<String> args = new ArrayList<>();
        String argsRaw = content.substring(Venture.getPrefix().length() + commandName.length()).replaceAll("^\\s+", "");
        if (argsRaw.length() > 0) args.addAll(Arrays.asList(argsRaw.split("\\s+")));

        CommandEvent cmdEvent = new CommandEvent(event);
        cmdEvent.setCommandName(commandName);
        cmdEvent.setArguments(args);

        //Running the command through the handler
        Venture.getCmdHandler().runCommand(cmdEvent);
    }
}
