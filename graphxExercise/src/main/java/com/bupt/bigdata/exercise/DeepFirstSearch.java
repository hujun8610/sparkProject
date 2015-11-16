package com.bupt.bigdata.exercise;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by hujun on 2015/11/16.
 */
public class DeepFirstSearch {

    public static List<Vertex> deepFirstSearch(Graph graph,Vertex vertex){
        int[][] matrix = graph.getGraphMatrix();
        Stack<Vertex> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();

        int rowIndex = vertex.getVertexId();
        int colIndex = 0;
        for (colIndex = 0; colIndex < matrix.length; colIndex++) {
            if(matrix[rowIndex][colIndex]!=0) break;
        }


        while (set.size()< matrix.length){
            System.out.println("The vertexId is "+matrix[rowIndex][colIndex]);

        }



        /*for (int i = 0; i < matrix.length; i++) {
            int vertexId = matrix[startVertexId][i];
            if(vertexId!=0&&!set.contains(vertexId)){
                System.out.println("The vertexId is "+vertexId);

            }
        }*/


       /**/


        return null;
    }

    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir");
        String fileName = "data/snapFile.txt";
        File file = new File(basePath,fileName);
        String regex = ",";

        List<Vertex> vertexList = Utils.getVertexsFromFile(file.getAbsolutePath(),regex);
        List<Edge> edgeList = Utils.getEdgesFromeFile(file.getAbsolutePath(), regex);

        Graph graph = new Graph(vertexList,edgeList);

        List<Vertex> resultList = deepFirstSearch(graph,graph.getVertexList().get(0));

       /* for (Vertex vertex : resultList) {
            System.out.println(vertex.getVertexId());
        }*/
    }
}
