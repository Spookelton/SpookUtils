package mixu.spookutils.main;

import com.google.gson.Gson;
import mixu.spookutils.helpers.FileHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

import static mixu.spookutils.SpookUtils.getSpookUtilsDirectory;

public class MutedPlayerChecker {
    static String filePath = getSpookUtilsDirectory() + "mutedPlayers.json";
    static ArrayList mutedPlayersCollection;
    static String mutedPlayersString;
    static Gson gson = new Gson();
    static boolean hasMutedPlayers = false;

    @SubscribeEvent
    public static void onMessage(ServerChatEvent event) {
        if (checkPlayer(event.getPlayer().getUniqueID().toString())) {
            event.setCanceled(true);
            event.getPlayer().sendStatusMessage(new TextComponentString(TextFormatting.RED + "You are muted!"), true);
        }
    }

    public static boolean checkPlayer(String playerUUID) {
        String plrUUID = "\""+playerUUID+"\"";
        if (mutedPlayersCollection.contains(plrUUID)) {
            return true;
        }
        return false;
    }

    public static void reloadMutedUsers() {
        mutedPlayersString = FileHelper.readFile(filePath);
        mutedPlayersCollection = gson.fromJson(mutedPlayersString, ArrayList.class);
        if (mutedPlayersCollection == null) { hasMutedPlayers = false; }
        else { hasMutedPlayers = true; }
    }
}
