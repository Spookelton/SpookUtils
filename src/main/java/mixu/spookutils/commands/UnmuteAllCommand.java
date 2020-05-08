package mixu.spookutils.commands;

import mixu.spookutils.base.CmdBase;
import mixu.spookutils.event.PlayerMuteStatusChangeEvent;
import mixu.spookutils.helpers.FileHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;

import static mixu.spookutils.SpookUtils.getSpookUtilsDirectory;

public class UnmuteAllCommand extends CmdBase {
    String filePath = getSpookUtilsDirectory() + "mutedPlayers.json";

    public UnmuteAllCommand() { super("unmuteAll", Level.OP); }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!server.isDedicatedServer()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable in a dedicated server"));
            return;
        }
        boolean success = FileHelper.writeFile(filePath, "[]");
        if (success) {
            sender.sendMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Unmuted all muted players"));
            MinecraftForge.EVENT_BUS.post(new PlayerMuteStatusChangeEvent(true));
        } else sender.sendMessage(new TextComponentString(TextFormatting.RED + "Failed to unmute all players"));
    }
}
