package com.bupt.bigdata.exercise;

/**
 * Created by hujun on 2015/11/15.
 */
public class Vertex {

    private int value;

    public Vertex(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                '}';
    }
}
