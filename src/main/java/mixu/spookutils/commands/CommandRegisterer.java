package mixu.spookutils.commands;

import mixu.spookutils.config.ModConfig;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegisterer {
    public static void registerCommands(FMLServerStartingEvent event) {
        if (ModConfig.Commands.dumpDimensions) { event.registerServerCommand(new DumpDimensionsCommand()); }
        if (ModConfig.Commands.mute) {
            event.registerServerCommand(new MuteCommand());
            event.registerServerCommand(new UnmuteCommand());
            event.registerServerCommand(new UnmuteAllCommand());
            event.registerServerCommand(new ListMutedCommand());
        }
    }
}
