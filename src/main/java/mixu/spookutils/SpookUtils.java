package mixu.spookutils;

import mixu.spookutils.packet.NetworkHandler;
import mixu.spookutils.proxy.CommonProxy;
import mixu.spookutils.commands.reDumpDimensions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SpookUtils.MODID, name = SpookUtils.NAME, version = SpookUtils.VERSION)
public class SpookUtils
{
    public static final String MODID = "spookutils";
    public static final String NAME = "SpookUtils";
    public static final String VERSION = "1.2.0";

    @SidedProxy(clientSide = "mixu.spookutils.proxy.ClientProxy", serverSide = "mixu.spookutils.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static SpookUtils instance;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
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
        event.registerServerCommand(new reDumpDimensions());
    }
}
