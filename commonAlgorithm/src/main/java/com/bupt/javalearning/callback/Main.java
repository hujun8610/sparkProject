package com.bupt.javalearning.callback;

/**
 * Created by hujun on 2016/4/16.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("hello callback");
    }

}
