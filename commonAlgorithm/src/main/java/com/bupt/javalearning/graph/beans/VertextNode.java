package com.bupt.javalearning.graph.beans;

/**
 * �ڽӱ���ʽͼ�Ķ��� ����
 * */

public class VertextNode{

    private int data;
    private ArcNode arcNode;

    public VertextNode(int data) {
        this.data = data;
        this.arcNode = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ArcNode getArcNode() {
        return arcNode;
    }

    public void setArcNode(ArcNode arcNode) {
        this.arcNode = arcNode;
    }
}
