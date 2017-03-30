package com.jylu.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ClassName: IOUtil <br/>
 * Description: IO工具类 <br/>
 * Date: 17-3-23 下午9:09 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class IOUtil {

    private IOUtil(){}

    /**
     * 字符流复制文件
     * @param from 源文件
     * @param to 目标文件
     */
    public static void copyFileChar(String from, String to){
        File fromFile = new File(from);
        File toFile = new File(to);
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(fromFile));
            bw = new BufferedWriter(new FileWriter(toFile));
            String content;
            while(null != (content = br.readLine())){
                bw.write(content);
                bw.newLine();
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != br){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != bw){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字节流复制文件
     * @param from
     * @param to
     */
    public static void copyFileByte(String from, String to){
        File fromFile = new File(from);
        File toFile = new File(to);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(fromFile));
            bos = new BufferedOutputStream(new FileOutputStream(toFile));

            // 定义字节数组
            byte[] byteArray = new byte[1024];
            while(-1 != (bis.read(byteArray))){
                bos.write(byteArray);
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != bos){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != bis){
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用NIO的管道来复制文件
     * @param from
     * @param to
     */
    public static void copyFileByNioMap(String from, String to){
        Path fromPath = Paths.get(from);
        Path toPath = Paths.get(to);
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = FileChannel.open(fromPath, StandardOpenOption.READ);
            // StandardOpenOption.CREATE:不存在即创建,存在覆盖
            // StandardOpenOption.CREATE_NEW:不存在创建,存在报错
            toChannel = FileChannel.open(toPath,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.READ,
                    StandardOpenOption.CREATE);
            // MapMode.READ_ONLY:该内存映射为只读
            // mappedByteBuffer:内存映射文件,现在的缓冲区在物理内存中
            // 内存映射文件只有ByteBuffer支持
            MappedByteBuffer fromMappedByteBuffer = fromChannel.map(FileChannel.MapMode.READ_ONLY,
                    0, fromChannel.size());
            MappedByteBuffer toMappedByteBuffer = toChannel.map(FileChannel.MapMode.READ_WRITE,
                    0, toChannel.size());

            // 直接操作缓冲区进行数据的读写操作
            byte[] dst = new byte[fromMappedByteBuffer.limit()];

            fromMappedByteBuffer.get(dst);
            toMappedByteBuffer.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fromChannel){
                    fromChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != toChannel){
                    toChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFileNio(String fromPath, String toPath){
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            fromChannel = fis.getChannel();
            toChannel = fos.getChannel();
            // 分配一个指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(-1 != (fromChannel.read(buffer))){
                // 将缓冲区从读取模式切换到写模式
                buffer.flip();
                // 将缓冲区数据写入到通道
                toChannel.write(buffer);
                // 清空缓冲区重新进入写模式
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                toChannel.close();
                fromChannel.close();
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFileNioTransfer(String fromPath, String toPath){
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = FileChannel.open(Paths.get(fromPath), StandardOpenOption.READ);
            toChannel = FileChannel.open(Paths.get(toPath),
                    StandardOpenOption.WRITE,
                    StandardOpenOption.READ,
                    StandardOpenOption.CREATE);
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
            // toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test6() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        // 获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuff = CharBuffer.allocate(1024);
        cBuff.put("计算机科学与技术");
        cBuff.flip();

        // 编码:字符串->字节数组
        ByteBuffer bBuff = ce.encode(cBuff);
        for(int i = 0; i < 16; i++){
            System.out.println(bBuff.get());
        }
        // 解码
        bBuff.flip();
        System.out.println(cd.decode(bBuff).toString());
    }

    /**
     * 对输入的文本按照指定的编码进行解码
     * @param content
     * @param charSet
     */
    public static String decodeString(String content, String charSet) throws CharacterCodingException {
        Charset cs = Charset.forName(charSet);
        // 获取编码器
        CharsetEncoder encoder = cs.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = cs.newDecoder();

        char[] charArray = content.toCharArray();
        CharBuffer cBuff = CharBuffer.allocate(charArray.length);
        cBuff.put(charArray);
        cBuff.flip();
        // 编码:字符串->字节数组
        ByteBuffer bBuff = null;
        try {
            bBuff = encoder.encode(cBuff);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        bBuff.flip();
        return decoder.decode(bBuff).toString();
    }

}