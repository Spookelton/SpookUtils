package mixu.spookutils.event;

import mixu.spookutils.SpookUtils;
import mixu.spookutils.commands.ListMutedCommand;
import mixu.spookutils.main.MutedPlayerChecker;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MuteCommandEventHandler {
    @SubscribeEvent
    public static void onMuteStatusChange(PlayerMuteStatusChangeEvent event) {
        MutedPlayerChecker.reloadMutedUsers();
        ListMutedCommand.reloadMutedUsers();
    }
}
