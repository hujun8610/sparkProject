package com.bupt.javalearning.callback;

/**
 * Created by hujun on 2016/4/16.
 */
public class Client implements CallBack {
    private Server server;

    public Client(Server server){
        this.server = server;
    }

    public void sendMsg(final String msg){
        System.out.println("client send msg is "+msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.getClientMsg(Client.this,msg);
            }
        }).start();

    }

    @Override
    public void process(String msg) {
        System.out.println("client get the message from Server is "+msg);
    }
}
