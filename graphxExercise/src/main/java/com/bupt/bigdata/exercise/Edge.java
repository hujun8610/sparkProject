package com.bupt.bigdata.exercise;

/**
 * Created by hujun on 2015/11/15.
 */
public class Edge {
    private Vertex src;
    private Vertex dst;
    private int weight;

    public Edge(Vertex src,Vertex dst,int weight){
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public Vertex getSrc() {
        return src;
    }

    public void setSrc(Vertex src) {
        this.src = src;
    }

    public Vertex getDst() {
        return dst;
    }

    public void setDst(Vertex dst) {
        this.dst = dst;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dst=" + dst +
                ", weight=" + weight +
                '}';
    }
}
