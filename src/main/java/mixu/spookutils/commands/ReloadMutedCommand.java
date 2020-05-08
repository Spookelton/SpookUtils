package mixu.spookutils.commands;

import com.google.gson.Gson;
import mixu.spookutils.base.CmdBase;
import mixu.spookutils.helpers.FileHelper;
import mixu.spookutils.main.MutedPlayerChecker;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ReloadMutedCommand extends CmdBase {

    public ReloadMutedCommand() { super("reloadMuted", CmdBase.Level.OP); }

    public static void reloadMutedUsers() {
        ListMutedCommand.reloadMutedUsers();
        MutedPlayerChecker.reloadMutedUsers();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!server.isDedicatedServer()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable in a dedicated server"));
        }
        reloadMutedUsers();
        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Reloaded muted users"));
    }
}
