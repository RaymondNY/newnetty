package com.example.newnetty.ch5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufExamples {
    private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);
    public static void heapBuffer() {
        ByteBuf heapBuf = BYTE_BUF_FROM_SOMEWHERE; //get reference form somewhere
        //检查 ByteBuf 是否有一个支撑数组
        heapBuf.writeByte(1);
        if (heapBuf.hasArray()) {
            //如果有，则获取对该数组的引用
            byte[] array = heapBuf.array();
            //计算第一个字节的偏移量
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            //获得可读字节数
            int length = heapBuf.readableBytes();
            //使用数组、偏移量和长度作为参数调用你的方法
            handleArray(array, offset, length);
        }
    }
    public static void directBuffer() {
        ByteBuf directBuf = Unpooled.directBuffer(1024);
        String a ="哈";
        directBuf.writeBytes(a.getBytes());
        //get reference form somewhere
        //检查 ByteBuf 是否由数组支撑。如果不是，则这是一个直接缓冲区
        if (!directBuf.hasArray()) {
            //获取可读字节数
            int length = directBuf.readableBytes();
            //分配一个新的数组来保存具有该长度的字节数据
            byte[] array = new byte[length];
            //将字节复制到该数组
            directBuf.getBytes(directBuf.readerIndex(), array);
            //使用数组、偏移量和长度作为参数调用你的方法
            System.out.println(new String(array));
            handleArray(array, 0, length);
        }
    }
    private static void handleArray(byte[] array, int offset, int len) {}

    public static void main(String[] args) {
        //ByteBufExamples.directBuffer();
        Charset utf8 = Charset.forName("UTF-8");
        //创建一个新的 ByteBuf 以保存给定字符串的字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        //打印第一个字符'N'
        System.out.println((char)buf.readByte());
        //存储当前的readerIndex
        int readerIndex = buf.readerIndex();
        //存储当前的writerIndex
        int writerIndex = buf.writerIndex();
        //将字符 '?'追加到缓冲区
        buf.writeByte((byte)'?');
        System.out.println(readerIndex == buf.readerIndex());
        System.out.println(writerIndex != buf.writerIndex());
    }
}
