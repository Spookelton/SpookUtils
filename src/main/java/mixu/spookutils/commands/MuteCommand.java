package mixu.spookutils.commands;

import com.google.gson.Gson;
import com.mojang.authlib.GameProfile;
import mixu.spookutils.SpookUtils;
import mixu.spookutils.base.CmdBase;
import mixu.spookutils.event.PlayerMuteStatusChangeEvent;
import mixu.spookutils.helpers.FileHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static mixu.spookutils.SpookUtils.getSpookUtilsDirectory;
import static mixu.spookutils.SpookUtils.proxy;

public class MuteCommand extends CmdBase {

    public MuteCommand() { super("mute", Level.OP); }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        checkArgs(sender, args, 1);
        if (!server.isDedicatedServer()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable in a dedicated server"));
            return;
        }
        Gson gson = new Gson();
        ArrayList mutedPlayersCollection;
        String mutedPlayersString = null;
        AtomicBoolean playerExists = new AtomicBoolean(false);
        boolean empty = false;

        if (!proxy.SpookUtilsFolderExists()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "SpookUtils folder not found/couldn't be created"));
            return;
        }

        String filePath = getSpookUtilsDirectory() + "mutedPlayers.json";
        boolean success = FileHelper.createFile(filePath);
        if (!success) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Failed to create muted players file"));
            return;
        }
        //welcome to spaghetti code hell

        mutedPlayersString = FileHelper.readFile(filePath);
        mutedPlayersCollection = gson.fromJson(mutedPlayersString, ArrayList.class);

        if (mutedPlayersCollection == null) {
            //sender.sendMessage(new TextComponentString(TextFormatting.RED + "Muted players list is empty"));
            mutedPlayersCollection = new ArrayList<String>();
        }

        GameProfile playerProfile = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);
        String playerUUID = playerProfile.getId().toString();

        mutedPlayersCollection.forEach(k -> {
            if (k.equals("\""+playerUUID+"\"")) {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "Player is already muted"));
                playerExists.set(true);
            }
        });

        if (!playerExists.get()) {
            String writeJson = gson.toJson(playerUUID);
            mutedPlayersCollection.add(writeJson);

            String writeJson2 = gson.toJson(mutedPlayersCollection);

            boolean success2 = FileHelper.writeFile(filePath, writeJson2);

            if (!success2) {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "Failed to write muted players list"));
            } else {
                sender.sendMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Player "+ args[0] +" muted successfully! (UUID of player is "+ playerUUID +")"));
                MinecraftForge.EVENT_BUS.post(new PlayerMuteStatusChangeEvent(playerProfile, true));
            }
        }
    }
}
