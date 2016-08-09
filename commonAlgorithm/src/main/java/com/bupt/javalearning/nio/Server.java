package com.bupt.javalearning.nio;



import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hujun on 2016/8/8.
 */
public class Server {

    private static final Logger logger = Logger.getLogger(Server.class);
    private String ip;
    private int port;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Selector register(){
        ServerSocketChannel serverSocketChannel = null;
        InetSocketAddress address = new InetSocketAddress(port);
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(address);
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            return selector;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listen(Selector selector){
        try {
            while (true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();
                    process(key);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    private void process(SelectionKey key) {
        Protocol protocol = new TCProtocol();
        if(key.isReadable()){
            protocol.handleRead(key);
        }else if(key.isWritable()){
            protocol.handleWrite(key);
        }else {
            protocol.handleAccepted(key);
        }
    }


    public static void main(String[] args) {
        String ip = "localhost";
        int port = 65432;

        Server server = new Server(ip,port);
        Selector selector = server.register();
        server.listen(selector);

        //Todo how to shut down ServerSocketChannel

    }

}
