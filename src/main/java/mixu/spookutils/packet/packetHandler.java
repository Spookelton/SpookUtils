package mixu.spookutils.packet;

import mixu.spookutils.SpookUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import mixu.spookutils.packet.PacketReDumpDimensions;
import net.minecraftforge.fml.relauncher.Side;

public class packetHandler {
    public static SimpleNetworkWrapper net;

    public static void initPackets() {
        net = NetworkRegistry.INSTANCE.newSimpleChannel(SpookUtils.MODID.toUpperCase());

        net.registerMessage(PacketReDumpDimensions.class, PacketReDumpDimensions.Message.class, 0, Side.SERVER);
        net.registerMessage(PacketReDumpDimensions.class, PacketReDumpDimensions.Message.class, 0, Side.SERVER);
    }
}
