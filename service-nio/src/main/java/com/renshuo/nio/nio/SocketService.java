package com.renshuo.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lenovo on 2023/6/19.
 */
public class SocketService {


    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {



        ServerSocketChannel sc = ServerSocketChannel.open();

        sc.socket().bind(new InetSocketAddress(9000));
        //设置socket为非阻塞
        sc.configureBlocking(false);
        System.out.println("启动成功");

        while(true){
            SocketChannel channel = sc.accept();
            if(channel!=null){
                System.out.println("链接成功");
                channel.configureBlocking(false);
                channelList.add(channel);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            while(iterator.hasNext()){
                SocketChannel isc = iterator.next();

                ByteBuffer bb = ByteBuffer.allocate(128);
                int len =  isc.read(bb);

                if(len>0){
                    System.out.println("接收消息："+new String(bb.array(),"UTF-8"));
                }else if(len==-1){

                    iterator.remove();
                    System.out.println("断开链接");
                }
            }

        }

    }

}
