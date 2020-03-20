package mixu.spookutils;

import mixu.spookutils.commands.CommandRegisterer;
import mixu.spookutils.commands.ListMutedCommand;
import mixu.spookutils.event.CommandEventHandler;
import mixu.spookutils.event.MuteCommandEventHandler;
import mixu.spookutils.helpers.FileHelper;
import mixu.spookutils.main.MutedPlayerChecker;
import mixu.spookutils.packet.NetworkHandler;
import mixu.spookutils.proxy.CommonProxy;
import mixu.spookutils.commands.DumpDimensionsCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = SpookUtils.MODID, name = SpookUtils.NAME, version = SpookUtils.VERSION, acceptableRemoteVersions = "*")
public class SpookUtils
{
    public static final String MODID = "spookutils";
    public static final String NAME = "SpookUtils";
    public static final String VERSION = "1.3.0";

    @SideOnly(Side.SERVER)
    public static String getSpookUtilsDirectory() {
        return FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+File.separator+"SpookUtils"+ File.separator;
    }

    @SidedProxy(clientSide = "mixu.spookutils.proxy.ClientProxy", serverSide = "mixu.spookutils.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static SpookUtils instance;

    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = LogManager.getLogger("SpookUtils");
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        NetworkHandler.init();
        MinecraftForge.EVENT_BUS.register(MuteCommandEventHandler.class);
        MinecraftForge.EVENT_BUS.register(CommandEventHandler.class);
        MinecraftForge.EVENT_BUS.register(MutedPlayerChecker.class);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        CommandRegisterer.registerCommands(event);
        if (event.getServer().isDedicatedServer()) {
            FileHelper.createFile(getSpookUtilsDirectory() + "mutedPlayers.json");
            MutedPlayerChecker.reloadMutedUsers();
            ListMutedCommand.reloadMutedUsers();
        }
    }
}
