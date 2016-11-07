package com.bupt.javalearning.mutithread.designpattern.boundedbuffer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hujun on 16/9/25.
 */
public class Main {
    private static final int MAXSIZE = 20;
    private static int consumerThreadNum = 1;
    private static int producerThreadNum = 1;

    public static void main(String[] args) {
        Queue<Cake> cakeQueue = new LinkedBlockingQueue<Cake>(MAXSIZE);

        for (int i = 0; i < producerThreadNum; i++) {
            Producer producer = new Producer(cakeQueue,i,MAXSIZE);
            new Thread(producer).start();
        }

        for (int i = 0; i < consumerThreadNum; i++) {
            Consumer consumer = new Consumer(cakeQueue,i);
            new Thread(consumer).start();
        }

    }

}
