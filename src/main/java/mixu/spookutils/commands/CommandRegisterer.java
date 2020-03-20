package mixu.spookutils.commands;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegisterer {
    public static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new DumpDimensionsCommand());
        event.registerServerCommand(new MuteCommand());
        event.registerServerCommand(new UnmuteCommand());
        event.registerServerCommand(new UnmuteAllCommand());
        event.registerServerCommand(new ListMutedCommand());
    }
}
