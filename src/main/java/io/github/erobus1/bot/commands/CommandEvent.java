package io.github.erobus1.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class CommandEvent extends GuildMessageReceivedEvent {

    private MessageCommand command;
    private String commandName;
    private List<String> arguments;

    public CommandEvent(GuildMessageReceivedEvent originalEvent) {
        super(originalEvent.getJDA(), 0, originalEvent.getMessage());
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public String getArgument(int index) {
        return arguments.get(index);
    }

    public MessageCommand getCommand() {
        return command;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public void setCommand(MessageCommand command) {
        this.command = command;
    }

    public void reply(String message) {
        getChannel().sendMessage(message).queue();
    }

    public void reply(EmbedBuilder embed) {
        getChannel().sendMessageEmbeds(embed.build()).queue();
    }

    public void reply(MessageEmbed embed) {
        getChannel().sendMessageEmbeds(embed).queue();
    }

    public boolean hasNoArgs() {
        return getArguments().size() == 0;
    }
}
