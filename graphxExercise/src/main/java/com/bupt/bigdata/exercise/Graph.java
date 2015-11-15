package com.bupt.bigdata.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hujun on 2015/11/15.
 */
public class Graph {

    private List<Vertex> vertexList;
    private int[][] graphMatrix;

    public Graph(List<Vertex> vertexList,List<Edge> edgeList){
        this.vertexList = vertexList;
        this.graphMatrix = new int[vertexList.size()][vertexList.size()];

       /* Map<Vertex,Integer> hashMap = new HashMap<>(this.vertexList.size());
        for (int i = 0; i < vertexList.size(); i++) {
            hashMap.put(vertexList.get(i),i);
        }*/

        for (Edge edge:edgeList){
            this.graphMatrix[edge.getSrc().getVertexId()][edge.getDst().getVertexId()] = edge.getWeight();
        }
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public int[][] getGraphMatrix() {
        return graphMatrix;
    }

    public void setGraphMatrix(int[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
    }
}
