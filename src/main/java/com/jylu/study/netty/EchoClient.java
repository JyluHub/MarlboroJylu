package com.jylu.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: EchoClient <br/>
 * Description: client <br/>
 * Date: 17-6-10 下午4:44 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class EchoClient {

    private final String host;
    private final int port;

    private EchoClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            // 1. 绑定线程组,处理读写和连接事件
            bootstrap.group(group)
                    // 2. 绑定客户端通道
                    .channel(NioSocketChannel.class)
                    // 3.
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                            ch.pipeline().addLast("LengthFieldPrepender", new LengthFieldPrepender(4));
//                            ch.pipeline().addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
//                            ch.pipeline().addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    })
                    .remoteAddress(new InetSocketAddress(host, port));
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for(int i = 0; i < 100; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        new EchoClient("localhost", 8888).start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}