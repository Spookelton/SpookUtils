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

public class UnmuteCommand extends CmdBase {

    public UnmuteCommand() { super("unmute", Level.OP); }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        checkArgs(sender, args, 1);
        if (!server.isDedicatedServer()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "This command is only usable in a dedicated server"));
            return;
        }
        Gson gson = new Gson();
        String filePath = getSpookUtilsDirectory() + "mutedPlayers.json";
        ArrayList mutedPlayersCollection;
        String player = null;
        String mutedPlayersString = null;
        AtomicBoolean playerFound = new AtomicBoolean(false);

        if (!proxy.SpookUtilsFolderExists()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "SpookUtils folder not found/couldn't be created"));
            return;
        }

        boolean success = FileHelper.createFile(filePath);
        if (!success) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Failed to create muted players file"));
            return;
        }

        mutedPlayersString = FileHelper.readFile(filePath);
        mutedPlayersCollection = gson.fromJson(mutedPlayersString, ArrayList.class);

        if (mutedPlayersCollection == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Muted players list is empty(No muted players)"));
            return;
        }

        GameProfile playerProfile = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);
        String playerUUID = playerProfile.getId().toString();

        mutedPlayersCollection.forEach(k -> {
            SpookUtils.logger.log(org.apache.logging.log4j.Level.INFO, k);
            if (k.equals("\""+playerUUID+"\"")) {
                playerFound.set(true);
                sender.sendMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Unmuted player "+ playerProfile.getName() +"! (UUID of player is "+ playerUUID +")"));
                MinecraftForge.EVENT_BUS.post(new PlayerMuteStatusChangeEvent(playerProfile, false));
            }
        });

        if (!playerFound.get()) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Player not currently muted"));
        } else if (playerFound.get()) {
            mutedPlayersCollection.remove("\""+playerUUID+"\"");
            String writeJson = gson.toJson(mutedPlayersCollection);
            FileHelper.writeFile(filePath, writeJson);
        }
    }

}
