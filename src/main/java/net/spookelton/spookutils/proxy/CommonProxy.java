package net.spookelton.spookutils.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy {
    public abstract void preInit();

    public abstract void init();

    public abstract void postInit();

    public abstract EntityPlayer getClientPlayer();

    public abstract boolean SpookUtilsFolderExists();
}
