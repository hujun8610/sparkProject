package com.bupt.javalearning.callback;

/**
 * Created by hujun on 2016/4/16.
 */
public class Server {

    public void getClientMsg(CallBack callBack,final String msg){
        System.out.println("Server get the message from client is "+msg);
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.process(msg);
    }

}
