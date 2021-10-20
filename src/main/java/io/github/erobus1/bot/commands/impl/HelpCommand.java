package io.github.erobus1.bot.commands.impl;

import io.github.erobus1.Venture;
import io.github.erobus1.bot.commands.Command;
import io.github.erobus1.bot.commands.CommandEvent;
import io.github.erobus1.bot.commands.MessageCommand;
import io.github.erobus1.bot.commands.util.CommandHelper;
import io.github.erobus1.internal.Note;
import io.github.erobus1.internal.embeds.DefaultEmbeds;
import io.github.erobus1.internal.exceptions.InvalidUsageException;
import net.dv8tion.jda.api.EmbedBuilder;


@Note(content = "Implemented help command created in the framework. If you wish to disable the command, call Venturebuilder#noHelp before building the client!")
@Command(
        name = "help",
        description = "Displays useful information about a command or category",
        usage = "[Command or Category]"
)
public class HelpCommand implements MessageCommand {
    @Override
    public void execute(CommandEvent event) {
        EmbedBuilder embed = DefaultEmbeds.DEFAULT();
        embed.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());
        embed.setTitle("Help");
        if (event.hasNoArgs()) {
            embed.setDescription(Venture.getBotInfo());
            embed.addField("Please provide either a command or a category. Available categories:", "`" + String.join("`, `", Venture.getCmdHandler().getCategories().toArray(new String[0])) + "`", false);
            event.reply(embed);
            return;
        }

        String argument = event.getArgument(0).toLowerCase();
        argument = argument.substring(0, 1).toUpperCase() + argument.substring(1);

        if (Venture.getCmdHandler().getCategories().contains(argument)) {
            MessageCommand[] commands = Venture.getCommands();
            embed.setDescription("__Here are all available commands in the `" + argument + "` category:__");
            embed.addBlankField(false);
            for (MessageCommand command : commands) {
                Command cmdInfo = CommandHelper.getInfoAnnotation(command);
                embed.addField("- " + cmdInfo.name(), cmdInfo.description(), false);
            }
            event.reply(embed);
            return;
        }

        MessageCommand command = Venture.getCmdHandler().getCommand(argument.toLowerCase());
        if (command != null) {
            Command cmdInfo = CommandHelper.getInfoAnnotation(command);
            embed.setDescription("**" + argument + " command help**\n\n" + cmdInfo.description());

            if (cmdInfo.usage().length() > 0) {
                embed.addField("Usage", cmdInfo.usage(), false);
                try {
                    embed.addField("Minimum arguments", String.valueOf(CommandHelper.parseUsage(command).getMinArgs()), false);
                } catch (InvalidUsageException ignored) {}
            }
            if (cmdInfo.aliases().length > 0) embed.addField("Aliases", String.join(", ", cmdInfo.aliases()), false);
            embed.addField("Category", cmdInfo.category(), false);
            embed.addField("Permission required", cmdInfo.permission().getName(), false);
            event.reply(embed);
            return;
        }


        event.reply(DefaultEmbeds.ERROR().setTitle("Not found").setDescription("No such command or category exists!"));
    }
}
