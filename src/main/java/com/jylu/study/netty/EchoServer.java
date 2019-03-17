package com.jylu.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * ClassName: EchoServer <br/>
 * Description: EchoServer <br/>
 * Date: 17-6-4 下午10:52 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class EchoServer {

    private final int port;

    private EchoServer(int port){
        this.port = port;
    }

    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 1. 绑定两个线程组,分别用来处理客户端通道的accept事件和读写事件
            bootstrap.group(bossGroup, workerGroup)
                    // 2. 绑定服务端的通道NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // 3. 给读写事件的线程通道绑定handel去真正处理读写事件
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                            ch.pipeline().addLast("LengthFieldPrepender", new LengthFieldPrepender(4));
//                            ch.pipeline().addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
//                            ch.pipeline().addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                            ch.pipeline().addLast("EchoServerHandler", serverHandler);
                        }
                    })
                    // 4. 监听端口
                    .localAddress(new InetSocketAddress(port));
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8888).start();
    }
}