package com.bupt.javalearning.mutithread.designpattern.boundedbuffer;

import java.util.Queue;

/**
 * Created by hujun on 16/9/24.
 */
public class Producer implements Runnable {

    private int id;
    private boolean keeprunning = true;
    private static int cakeId = 0;
    private Queue<Cake> cakeQueue;
    private int queueMaxSize = 0;


    public void setKeeprunning(boolean keeprunning) {
        this.keeprunning = keeprunning;
    }

    public Producer(Queue<Cake> cakeQueue,int id,int queueMaxSize) {
        this.cakeQueue = cakeQueue;
        this.id = id;
        this.queueMaxSize = queueMaxSize;
    }

    public synchronized void produceCake() {
        if (cakeQueue.size() > queueMaxSize) {
            try {
                Thread.currentThread().wait();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Cake cake = new Cake(cakeId);
        System.out.println("Produce 第 " + cakeId +" Cake");
        cakeQueue.offer(cake);
        notifyAll();
    }


    @Override
    public void run() {
        while (keeprunning){
            produceCake();

        }

        if(cakeQueue.size()>0){
            synchronized (this){
                notifyAll();
            }
        }

        System.out.println("Producer " + id + "exit");
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                '}';
    }
}
