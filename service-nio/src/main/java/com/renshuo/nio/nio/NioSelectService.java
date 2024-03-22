package com.renshuo.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lenovo on 2023/6/19.
 */
public class NioSelectService {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel  server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9000));
        server.configureBlocking(false);

        //设置selector
        Selector selector=Selector.open();
        //把启动的server注册到selector上，类型是对客户端的链接操作accept
        server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("启动成功");
        while(true){
            //阻塞等待需要处理的事件发生
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                //判断selector的可以是accept链接还是read读取
                if(key.isAcceptable()){
                    //链接的话强制转换为ServerSocketChannel设置read读取事件
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                    System.out.println("客户端链接成功");
                }else if(key.isReadable()){
                    //读取事件强制转换成SocketChannel，进行读取数据
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer bb = ByteBuffer.allocate(128);
                    int read = channel.read(bb);
                    if(read >0){
                        System.out.println("接收数据："+new String(bb.array()));
                    }else if(read==-1){
                        channel.close();
                        System.out.println("断开链接");
                    }
                }
                iterator.remove();
            }

        }



    }
}
