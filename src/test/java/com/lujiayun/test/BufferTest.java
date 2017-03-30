package com.lujiayun.test;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * ClassName: BufferTest <br/>
 * Description: Buffer缓冲区 <br/>
 * Date: 17-3-23 下午11:12 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class BufferTest {

    @Test
    public void BufferRead(){
        String message = "Hello my name is jylu";
        // 分配1024个字节的缓冲区,分配在用户空间内存中
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 分配1024个字节的直接缓冲区,直接分配在物理内存上
        // 直接在内核地址空间中,去掉由内核地址空间到用户空间地址空间的copy过程
        // 通过物理内存映射文件,资源消耗比较大,不安全,不受控制,由操作系统去控制
        //ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        buffer.put(message.getBytes());
        // flip方法将缓冲区从写模式转换到读模式,指针发生变化
        buffer.flip();
        byte[] dest = new byte[buffer.limit()];
        buffer.get(dest);
        System.out.println(new String(dest, 0, dest.length));
        // rewind方法重新将buffe恢复到读模式
        buffer.rewind();
        buffer.put(new String("Hello too").getBytes());
        buffer.flip();
        dest = new byte[buffer.limit()];
        buffer.get(dest);
        System.out.println(new String(dest, 0, dest.length));
        // clear方法将缓冲区置回最初状态,但是里面的数据并没有被清空,这些数据处于"被遗忘"状态,不能正确的去读取数据了
        buffer.clear();
    }
}