package com.bupt.javalearning.graph.beans;

import java.util.List;

/**
 * Created by hujun on 2016/6/10.
 */
public class Graph {
    List<VertextNode> vertextNodes;
    int vexnum;

    public Graph(List<VertextNode> vertextNodes, int vexnum) {
        this.vertextNodes = vertextNodes;
        this.vexnum = vexnum;
    }

    public List<VertextNode> getVertextNodes() {
        return vertextNodes;
    }

    public void setVertextNodes(List<VertextNode> vertextNodes) {
        this.vertextNodes = vertextNodes;
    }

    public int getVexnum() {
        return vexnum;
    }

    public void setVexnum(int vexnum) {
        this.vexnum = vexnum;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertextNodes=" + vertextNodes +
                ", vexnum=" + vexnum +
                '}';
    }
}
