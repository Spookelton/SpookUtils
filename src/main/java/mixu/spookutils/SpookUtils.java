package mixu.spookutils;

import mixu.spookutils.commands.CommandRegisterer;
import mixu.spookutils.helpers.FileHelper;
import mixu.spookutils.packet.NetworkHandler;
import mixu.spookutils.proxy.CommonProxy;
import mixu.spookutils.commands.DumpDimensionsCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = SpookUtils.MODID, name = SpookUtils.NAME, version = SpookUtils.VERSION, acceptableRemoteVersions = "*")
public class SpookUtils
{
    public static final String MODID = "spookutils";
    public static final String NAME = "SpookUtils";
    public static final String VERSION = "1.2.1";

    public static final String SpookUtilsDirectory = FMLServerHandler.instance().getSavesDirectory().getAbsolutePath()+File.separator+"SpookUtils"+ File.separator;

    @SidedProxy(clientSide = "mixu.spookutils.proxy.ClientProxy", serverSide = "mixu.spookutils.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
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
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        CommandRegisterer.registerCommands(event);
        FileHelper.createFile(SpookUtilsDirectory + "mutedPlayers.json");
    }
}
