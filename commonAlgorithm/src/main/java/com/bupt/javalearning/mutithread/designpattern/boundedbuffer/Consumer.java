package com.bupt.javalearning.mutithread.designpattern.boundedbuffer;

import java.util.Queue;

/**
 * Created by hujun on 16/9/24.
 */
public class Consumer implements Runnable{
    private int id;
    private Queue<Cake> cakeQueue;
    private boolean keeprunning = true;

    public Consumer(Queue<Cake> cakeQueue,int id) {
        this.id = id;
        this.cakeQueue = cakeQueue;
    }

    public synchronized void consumeCake(){
        if(cakeQueue.size()==0){
            System.out.println("The queue is empty,notify producer produce cake");
            notifyAll();
            return;
        }

        Cake cake = cakeQueue.poll();
        System.out.println("consume 第 " + cake.getId() + " cake");
        notifyAll();
    }


    @Override
    public void run() {
        while (keeprunning){
            consumeCake();
        }



    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", cakeQueue=" + cakeQueue +
                '}';
    }
}
