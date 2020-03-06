package mixu.spookutils.packet;

import io.netty.buffer.ByteBuf;
import mixu.spookutils.dimensions.dumpDimensions;
import mixu.spookutils.packet.PacketReDumpDimensions.Message;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketReDumpDimensions implements IMessageHandler<Message, IMessage> {
    @Override @SideOnly(Side.SERVER)
    public IMessage onMessage(Message message, MessageContext ctx) {
            dumpDimensions.dumpDimensions();
            return null;
    }

    public static class Message implements IMessage {
        public Message(){}

        private int toSend;
        public Message(int toSend) {
            this.toSend = toSend;
        }

        @Override public void toBytes(ByteBuf buf) {
            // Writes the int into the buf
            buf.writeInt(toSend);
        }

        @Override public void fromBytes(ByteBuf buf) {
            // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
            toSend = buf.readInt();
        }
    }
}
