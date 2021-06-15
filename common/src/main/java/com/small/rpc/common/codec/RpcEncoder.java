package com.small.rpc.common.codec;


import com.small.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RPC encoder
 *
 * @author daidai21
 */
@AllArgsConstructor
public class RpcEncoder extends MessageToByteEncoder {

    private static final Logger logger = LoggerFactory.getLogger(RpcEncoder.class);

    private Class<?> genericClass;

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            try {
                byte[] data = SerializationUtil.serialize(in);
                out.writeInt(data.length);
                out.writeBytes(data);
            } catch (Exception e) {
                logger.error("Encode error: " + in.toString() + " " + out.toString());
            }
        }
    }
}
