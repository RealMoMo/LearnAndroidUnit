package com.zl.weilu.androidut.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Realmo
 * @version 1.0.0
 * @name AndroidUT
 * @email momo.weiye@gmail.com
 * @time 2020/8/27 13:53
 * @describe
 *
 * IO
 * 面向流（Stream）
 *
 * NIO --- Non-blocking IO  (New IO)
 * 面向缓冲（Buffer）
 *
 * NIO 核心组成部分：
 * Channel
 * Buffer
 * Selector
 *
 *
 * =============================Channel============================
 * FileChannel  ---文件中读写数据
 * DatagramChannel  ---通过UDP读写网络的数据
 * SocketChannel    ---通过TCP读写网络的数据
 * ServerSocketChannel  ---可以监听新进来的TCP连接
 *
 * =============================Buffer=============================
 * ByteBuffer
 * CharBuffer
 * DoubleBuffer
 * FloatBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 * MappedByteBuffer
 *
 * ============================Selector=============================
 * 选择器
 * 用于监听多个Channel事件
 * 单个线程可监听多个数据通道
 *
 */
public class NioTest {

    private FileChannel channel;
    private ByteBuffer buffer;
    private Selector selector;


    private SocketChannel socketChannel;

    public void test() throws IOException {
        //获取channel
        channel = new FileInputStream("test.txt").getChannel();

        //创建buffer
        buffer = ByteBuffer.allocate(40);

        //读取数据
        channel.read(buffer);
        buffer.get();

        //创建Selector
        selector = Selector.open();

        socketChannel = SocketChannel.open();
        //向selector注册channel,这里的channel必须是非阻塞的。所以，这里不能注册FileChannel。IO/Socket是可以使用的。
        socketChannel.configureBlocking(false);
        //selector监听Channel的什么事件，若多个事件需要监听 ：SelectionKey.OP_READ | XXX
        SelectionKey key = socketChannel.register(selector,SelectionKey.OP_READ);

        //获取上面，监听的事件
        int interestOps = key.interestOps();
        boolean isAccept = (interestOps & SelectionKey.OP_ACCEPT) != 0;
//        boolean isConnect = interestOps & SelectionKey.OP_CONNECT;
//        boolean isRead = interestOps & SelectionKey.OP_READ;
//        boolean isWrite = interestOps & SelectionKey.OP_WRITE;


        //可以通过selectorkey 获取Channel 和Selector
        SelectableChannel channel = key.channel();
        Selector selector1 = key.selector();


    }


    private FileChannel fileChannel;

    public void testFileChannel() throws IOException {
        //1.创建FileChannel
        //不能直接创建FileChannel,但可以通过一些流 获取FileChannel
        fileChannel = new FileOutputStream(new File("")).getChannel();

        //2.读取数据
        ByteBuffer buffer = ByteBuffer.allocate(48);
        fileChannel.read(buffer);


        //3.写数据
        String data = "hfeahlggjdj dlkfjdalkje";
        buffer.clear();
        buffer.put(data.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        //4.关闭Channel
        channel.close();


        //5.文件大小byte
        long size = channel.size();


        //6.FileChannel截取,   截取指定长度的文件
        FileChannel truncate = channel.truncate(1024);

        //7.force   把还未写入的数据全部导入磁盘，类似与flush
        channel.force(true);


    }
}
