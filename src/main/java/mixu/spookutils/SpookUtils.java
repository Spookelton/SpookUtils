package mixu.spookutils;

import mixu.spookutils.commands.CommandRegisterer;
import mixu.spookutils.commands.ListMutedCommand;
import mixu.spookutils.config.ModConfig;
import mixu.spookutils.dimensions.DumpDimensions;
import mixu.spookutils.event.CommandEventHandler;
import mixu.spookutils.event.MuteCommandEventHandler;
import mixu.spookutils.helpers.FileHelper;
import mixu.spookutils.main.MutedPlayerChecker;
import mixu.spookutils.packet.NetworkHandler;
import mixu.spookutils.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static mixu.spookutils.restAPI.RestApiCore.startAPI;

@Mod(modid = SpookUtils.MODID, name = SpookUtils.NAME, version = SpookUtils.VERSION, acceptableRemoteVersions = "*")
public class SpookUtils
{
    public static final String MODID = "spookutils";
    public static final String NAME = "SpookUtils";
    public static final String VERSION = "1.4.0";

    private static boolean isDedicated;

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
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        NetworkHandler.init();
        ConfigManager.sync(SpookUtils.MODID, Type.INSTANCE);
        if (ModConfig.Commands.mute) {
        MinecraftForge.EVENT_BUS.register(MuteCommandEventHandler.class);
        MinecraftForge.EVENT_BUS.register(CommandEventHandler.class);
        MinecraftForge.EVENT_BUS.register(MutedPlayerChecker.class);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        CommandRegisterer.registerCommands(event);
        isDedicated = event.getServer().isDedicatedServer();
        if (isDedicated) DumpDimensions.dumpDimensions();
        if (event.getServer().isDedicatedServer() && ModConfig.Commands.mute) {
            FileHelper.createFile(getSpookUtilsDirectory() + "mutedPlayers.json", "[]");
            MutedPlayerChecker.reloadMutedUsers();
            ListMutedCommand.reloadMutedUsers();
        }
    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        if (isDedicated && ModConfig.restApi.enabled) {
        startAPI();
        }
    }

    @SubscribeEvent
    public void onConfigChange(OnConfigChangedEvent event) {
        if (event.getModID().equals(SpookUtils.MODID)) {

            ConfigManager.sync(SpookUtils.MODID, Type.INSTANCE);

        }
    }
}
