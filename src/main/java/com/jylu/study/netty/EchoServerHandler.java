package com.jylu.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * ClassName: EchoServerHandler <br/>
 * Description: EchoServerHandler: @Sharable注解表示一个ChannelHandler可以被多个Channel安全共享<br/>
 * Date: 17-6-4 下午10:55 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端通道的数据,并向客户端返回响应
     * @param ctx  ctx
     * @param msg  msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = null;
        if(msg instanceof ByteBuf) {
            in = (ByteBuf)msg;
            System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8) + ":" + ctx.channel().remoteAddress());
        }
        // 将收到的信息写回给发送者
        ctx.channel().writeAndFlush("Write to Client:" + UUID.randomUUID());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点,并且关闭该channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印堆栈信息
        cause.printStackTrace();
        // 关闭Channel
        ctx.close();
    }
}