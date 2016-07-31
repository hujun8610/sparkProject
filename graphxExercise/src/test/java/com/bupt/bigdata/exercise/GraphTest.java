package com.bupt.bigdata.exercise;

import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.util.List;

/**
 * Created by hujun on 2015/11/15.
 */
public class GraphTest {

    private String fileName ="e:\\IDEAWorkspace\\sparkProject\\" + "data"+ File.separator+"snapFile.txt";
    private String regex = ",";

    @Test
    public void testGraphConstructer() {
        List<Vertex> vertexList = Utils.getVertexsFromFile(fileName,regex);
        List<Edge> edgeList = Utils.getEdgesFromeFile(fileName, regex);
        Graph graph = new Graph(vertexList,edgeList);

        Assert.assertEquals(graph.getGraphMatrix()[0][1],5);
        Assert.assertEquals(graph.getGraphMatrix()[2][5],9);

    }


}
