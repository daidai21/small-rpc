package com.small.rpc.common.codec;


import com.small.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * RPC decoder
 *
 * @author daidai21
 */
@AllArgsConstructor
public class RpcDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(RpcDecoder.class);

    private Class<?> genericClass;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object obj = null;
        try {
            obj = SerializationUtil.deserialize(data, genericClass);
            out.add(obj);
        } catch (Exception e) {
            logger.error("Decoder error: " + in.toString() + " " + out.toString());
        }
    }
}
