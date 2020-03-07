package mixu.spookutils.proxy;

import mixu.spookutils.SpookUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import static mixu.spookutils.dimensions.dumpDimensions.dumpDimensions;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }
}
