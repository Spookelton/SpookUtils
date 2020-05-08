package mixu.spookutils.commands;

import mixu.spookutils.SpookUtils;
import mixu.spookutils.base.CmdBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import static mixu.spookutils.dimensions.DumpDimensions.dumpDimensions;

public class DumpDimensionsCommand extends CmdBase {

    public DumpDimensionsCommand() {
        super("dumpDimensions", Level.OP);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (sender instanceof EntityPlayer) {
            if (server.isDedicatedServer()) {
                Boolean success = dumpDimensions();
                if (success) {
                    SpookUtils.logger.log(org.apache.logging.log4j.Level.INFO, "Player " + ((EntityPlayer) sender).getName() + " (UUID " + ((EntityPlayer) sender).getUniqueID() + ") dumped dimensions");
                    sender.sendMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Successfully dumped dimensions"));
                } else {
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + "Failed to dump dimensions"));
                }
            } else {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable on a dedicated server"));
            }
        } else {
            if (server.isDedicatedServer()) {
                dumpDimensions();
                SpookUtils.logger.log(org.apache.logging.log4j.Level.INFO, "Non-player command sender " + sender.getName() + " dumped dimensions at " + sender.getPosition().toString());
            }
        }
    }
}
