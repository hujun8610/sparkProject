package com.bupt.bigdata.exercise;

/**
 * Created by hujun on 2015/11/15.
 */
public class Vertex {

    private int vertexId;
    private int value;

    public Vertex(int vertexId,int value){
        this.vertexId = vertexId;
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

    public int getVertexId() {
        return vertexId;
    }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }
}
