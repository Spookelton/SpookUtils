package net.spookelton.spookutils.config;

import net.spookelton.spookutils.SpookUtils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = SpookUtils.MODID)
public class ModConfig {
    public static final CONFIG_RESTAPI restApi = new CONFIG_RESTAPI();

    public static class CONFIG_RESTAPI {
        @Name("apiEnabled")
        @Comment("Whether or not to enable the rest api.")
        public boolean enabled = true;

        @Name("port")
        @Comment("The port the rest api will use")
        @RequiresMcRestart
        public int port = 30420;
    }
}
