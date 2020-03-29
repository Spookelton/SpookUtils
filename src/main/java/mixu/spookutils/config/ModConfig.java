<<<<<<< Updated upstream
package mixu.spookutils.config;

import mixu.spookutils.SpookUtils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = SpookUtils.MODID)
public class ModConfig {
    public static final CONFIG_RESTAPI restApi = new CONFIG_RESTAPI();
    public static final CONFIG_COMMAND Commands = new CONFIG_COMMAND();

    public static class CONFIG_RESTAPI {
        @Name("apiEnabled")
        @Comment("Whether or not to enable the rest api")
        public boolean enabled = true;
    }

    public static class CONFIG_COMMAND {
        @Comment("Enable muting commands")
        public boolean mute = true;
        @Comment("Enable dumpDimensions command")
        public boolean dumpDimensions = true;
    }
}
=======
package mixu.spookutils.config;

import mixu.spookutils.SpookUtils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = SpookUtils.MODID)
public class ModConfig {
    public static final CONFIG_RESTAPI restApi = new CONFIG_RESTAPI();
    public static final CONFIG_COMMAND Commands = new CONFIG_COMMAND();

    public static class CONFIG_RESTAPI {
        @Name("apiEnabled")
        @Comment("Whether or not to enable the rest api")
        public boolean enabled = true;
    }

    public static class CONFIG_COMMAND {
        @Comment("Enable muting commands")
        public boolean mute = true;
        @Comment("Enable dumpDimensions command")
        public boolean dumpDimensions = true;
    }
}
>>>>>>> Stashed changes
