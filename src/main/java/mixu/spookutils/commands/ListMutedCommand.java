package mixu.spookutils.commands;

import com.google.gson.Gson;
import mixu.spookutils.base.CmdBase;
import mixu.spookutils.helpers.FileHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.UUID;

import static mixu.spookutils.SpookUtils.getSpookUtilsDirectory;

public class ListMutedCommand extends CmdBase {
    static String filePath = getSpookUtilsDirectory() + "mutedPlayers.json";
    static ArrayList<String> mutedPlayersCollection;
    static String mutedPlayersString;
    static Gson gson = new Gson();
    static boolean hasMutedPlayers = false;

    public ListMutedCommand() { super("listMuted", Level.OP); }

    public static void reloadMutedUsers() {
        mutedPlayersString = FileHelper.readFile(filePath);
        mutedPlayersCollection = gson.fromJson(mutedPlayersString, ArrayList.class);
        if (mutedPlayersCollection == null) { hasMutedPlayers = false; }
        else { hasMutedPlayers = true; }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String mutedPlayers;

        if (!server.isDedicatedServer()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable in a dedicated server"));
        }
        if (hasMutedPlayers) {
            //lotsa stuff in one line
            mutedPlayers = (String) mutedPlayersCollection.stream().reduce(""/*<- I have no idea what this identity thing is*/, (a, b) -> (a+"\n"+server.getPlayerProfileCache().getProfileByUUID(UUID.fromString(b.replace("\"", ""))).getName()));
            mutedPlayers = mutedPlayers.replace("\"", "");
            mutedPlayers = "Muted players:" + mutedPlayers;
            sender.sendMessage(new TextComponentString(mutedPlayers));
        }
        else {sender.sendMessage(new TextComponentString(TextFormatting.RED + "No players are currently muted"));}
    }
}
