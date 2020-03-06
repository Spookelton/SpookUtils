package mixu.spookutils.proxy;

import mixu.spookutils.SpookUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import static mixu.spookutils.dimensions.dumpDimensions.dumpDimensions;

@Mod.EventBusSubscriber(Side.SERVER)
public class ServerProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        dumpDimensions();
    }

}
