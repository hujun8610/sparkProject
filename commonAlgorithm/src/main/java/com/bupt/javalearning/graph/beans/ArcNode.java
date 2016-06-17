package com.bupt.javalearning.graph.beans;

/**
 * Created by hujun on 2016/6/10.
 */
/**
 * 邻接表形式的图的定义-头结点
 * */
public class ArcNode {

    private int adjvex;
    private ArcNode next;
    private int data;

    public ArcNode(int adjvex, int data) {
        this.adjvex = adjvex;
        this.data = data;
        this.next = null;
    }

    public int getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public ArcNode getNext() {
        return next;
    }

    public void setNext(ArcNode next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArcNode{" +
                "adjvex=" + adjvex +
                ", next=" + next +
                ", data=" + data +
                '}';
    }
}

