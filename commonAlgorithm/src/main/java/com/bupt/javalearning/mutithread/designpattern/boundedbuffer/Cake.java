package com.bupt.javalearning.mutithread.designpattern.boundedbuffer;

/**
 * Created by hujun on 16/9/24.
 */
public class Cake {
    private int id;

    public Cake(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                '}';
    }
}
