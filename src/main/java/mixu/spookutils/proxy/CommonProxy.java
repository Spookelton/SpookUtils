package mixu.spookutils.proxy;

import net.minecraft.entity.player.EntityPlayer;

import java.io.File;

public abstract class CommonProxy {
    public abstract void preInit();

    public abstract void init();

    public abstract void postInit();

    public abstract EntityPlayer getClientPlayer();

    public abstract boolean SpookUtilsFolderExists();
}
