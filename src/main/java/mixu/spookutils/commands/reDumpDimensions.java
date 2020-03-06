package mixu.spookutils.commands;

import mixu.spookutils.SpookUtils;
import mixu.spookutils.base.CmdBase;
import mixu.spookutils.packet.PacketReDumpDimensions;
import mixu.spookutils.packet.packetHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.Level;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static mixu.spookutils.dimensions.dumpDimensions.dumpDimensions;

public class reDumpDimensions extends CmdBase {

    public reDumpDimensions() {
        super("dumpDimensions",Level.OP);
    }

    private static FileWriter file;

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (sender instanceof EntityPlayer) {
            //packetHandler.net.sendToServer(new PacketReDumpDimensions.Message(1));
            sender.sendMessage(new TextComponentString("Only works in console!"));
        } else {
            dumpDimensions();
            SpookUtils.logger.log(org.apache.logging.log4j.Level.INFO, "Dumped dimensions");
        }
    }
}
