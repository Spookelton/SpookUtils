package net.spookelton.spookutils;

import net.spookelton.spookutils.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import net.spookelton.spookutils.restAPI.ApiCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

import static net.spookelton.spookutils.restAPI.ApiCore.startAPI;

@Mod(
        modid = SpookUtils.MODID,
        name = SpookUtils.NAME,
        version = SpookUtils.VERSION,
        serverSideOnly = true
)
public class SpookUtils
{
    public static final String MODID = "spookutils";
    public static final String NAME = "SpookUtils";
    public static final String VERSION = "1.5.1";

    private static boolean isDedicated;
    
    @SideOnly(Side.SERVER)
    public static Path getSpookUtilsDirectory() {
        return Paths.get(
                FMLServerHandler.instance().getSavesDirectory().getAbsolutePath(),
                "SpookUtils"
        );
    }

    @Mod.Instance(MODID)
    public static SpookUtils instance;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = LogManager.getLogger("SpookUtils");
        MinecraftForge.EVENT_BUS.register(this);
        isDedicated = FMLServerHandler.instance().getServer().isDedicatedServer();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ConfigManager.sync(SpookUtils.MODID, Type.INSTANCE);
    }


    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        if (isDedicated && ModConfig.restApi.enabled) {
            new Thread(ApiCore::startAPI).start();
        } else {
            logger.warn("Running on integrated server, REST api not started");
        }
    }

    @SubscribeEvent
    public void onConfigChange(OnConfigChangedEvent event) {
        if (event.getModID().equals(SpookUtils.MODID)) {
            ConfigManager.sync(SpookUtils.MODID, Type.INSTANCE);
        }
    }
}
