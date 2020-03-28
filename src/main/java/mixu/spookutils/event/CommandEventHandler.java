package mixu.spookutils.event;

import mixu.spookutils.main.MutedPlayerChecker;
import net.minecraft.command.server.CommandBroadcast;
import net.minecraft.command.server.CommandEmote;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandEventHandler {
    @SubscribeEvent
    public static void onCommandExecuted(CommandEvent event) {
        if (event.getCommand() instanceof CommandMessage || event.getCommand() instanceof CommandEmote || event.getCommand() instanceof CommandBroadcast) {
            if (!(event.getSender() instanceof EntityPlayer)) {return;}
            if (MutedPlayerChecker.checkPlayer(event.getSender().getCommandSenderEntity().getUniqueID().toString())) {
                event.setCanceled(true);
                ((EntityPlayer) event.getSender()).sendStatusMessage(new TextComponentString(TextFormatting.RED + "You are muted!"),true);
            }
        }
    }
}
