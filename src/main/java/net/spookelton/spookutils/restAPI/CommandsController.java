package net.spookelton.spookutils.restAPI;

import net.minecraft.command.ICommand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommandsController {
    @GetMapping("/commands")
    public List<Command> getCommands() {
        final Collection<ICommand> commands = FMLServerHandler.instance().getServer()
                .commandManager.getCommands().values();
        return commands.stream().map(Command::new).collect(Collectors.toList());
    }

    @GetMapping("/command")
    public Command getCommand(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") List<String> arguments
    ) throws CommandNotFoundException {
        final ICommand command = FMLServerHandler.instance().getServer()
                .commandManager.getCommands().get(name);
        if (command != null) return new Command(command, arguments);
        throw new CommandNotFoundException(name);
    }

    @ExceptionHandler({CommandNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleException(CommandNotFoundException e) {
        return new Error(HttpStatus.NOT_FOUND.value(), "Command '" + e.getCommand() + "' not found");
    }

    private static class Command {
        private final String name;
        private final List<String> aliases;
        private final String usage;
        private final List<String> tabCompletions;

        public Command(ICommand command, List<String> args) {
            if (args.size() == 0) args.add("");
            name = command.getName();
            aliases = command.getAliases();
            usage = new TextComponentTranslation(
                    command.getUsage(FMLServerHandler.instance().getServer())
            ).getUnformattedText();
            tabCompletions = command.getTabCompletions(
                    FMLServerHandler.instance().getServer(),
                    FMLServerHandler.instance().getServer(),
                    args.toArray(new String[0]),
                    null
            );
        }

        public Command(ICommand command) {
            this(command, new ArrayList<>());
        }

        public String getName() {
            return name;
        }

        public List<String> getAliases() {
            return aliases;
        }

        public String getUsage() {
            return usage;
        }

        public List<String> getTabCompletions() {
            return tabCompletions;
        }
    }

    public static class CommandNotFoundException extends Exception {
        private final String command;

        CommandNotFoundException(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
}
