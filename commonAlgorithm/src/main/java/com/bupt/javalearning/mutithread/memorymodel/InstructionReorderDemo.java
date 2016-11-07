package com.bupt.javalearning.mutithread.memorymodel;

/**
 * Created by hujun on 2016/9/18.
 */

/**
 * 由于指令重排序的原因，下面的代码有可能输出
 * x=1,y=0
 * x=0,y=1
 * x=1,y=1
 * */
public class InstructionReorderDemo extends Thread{
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start();
        other.start();

        try {
            one.join();
            other.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("x = "+x+" y = "+y);

    }

}
