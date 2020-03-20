package mixu.spookutils.event;

import com.mojang.authlib.GameProfile;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerMuteStatusChangeEvent extends Event {

    private GameProfile player;
    private boolean muted;

    public PlayerMuteStatusChangeEvent(GameProfile player, boolean muted) {
        this.player = player;
        this.muted = muted;
    }

    public PlayerMuteStatusChangeEvent(boolean AllUnmuted) {
        this.player = null;
        this.muted = false;
    }

    public GameProfile getPlayer() { return this.player; }

    public boolean getMuted() { return this.muted; }

}
