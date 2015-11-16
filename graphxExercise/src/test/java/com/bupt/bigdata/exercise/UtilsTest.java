package com.bupt.bigdata.exercise;

import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.util.List;

/**
 * Created by hujun on 2015/11/15.
 */
public class UtilsTest {

    private String fileName ="e:\\IDEAWorkspace\\sparkProject\\" + "data"+ File.separator+"snapFile.txt";
    private String regex = ",";

    @Test
    public void testGetVertexsFromFile(){
        List<Vertex> list = Utils.getVertexsFromFile(fileName,regex);
        Assert.assertEquals(list.size(),6);

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i).getVertexId(),i);
            Assert.assertEquals(list.get(i).getValue(),i+1);
        }

    }

    @Test
    public void  testGetEdgesFromeFile(){
        List<Edge> list = Utils.getEdgesFromeFile(fileName, regex);
        Assert.assertEquals(list.size(), 10);

        int[] srcVertexId = new int[list.size()];
        int[] srcVertexValue = new int[list.size()];
        int[] dstVertexId = new int[list.size()];
        int[] dstVertexValue = new int[list.size()];
        int[] weight = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);

            srcVertexId[i] = edge.getSrc().getVertexId();
            srcVertexValue[i] = edge.getSrc().getValue();
            dstVertexId[i] = edge.getDst().getVertexId();
            dstVertexValue[i] = edge.getDst().getValue();
            weight[i] = edge.getWeight();
        }

        int[] expectedSrcVId = {0,0,1,2,2,3,3,4,5,5};
        int[] expectedSrcValue = {1,1,2,3,3,4,4,5,6,6};
        int[] expectedDstVId = {1,3,2,0,5,2,5,3,0,4};
        int[] expectedDstValue = {2,4,3,1,6,3,6,4,1,5};
        int[] expectWeight = {5,7,4,8,9,5,6,5,3,1};

        Assert.assertArrayEquals(expectedSrcVId,srcVertexId);
        Assert.assertArrayEquals(expectedSrcValue,srcVertexValue);
        Assert.assertArrayEquals(expectedDstVId,dstVertexId);
        Assert.assertArrayEquals(expectedDstValue,dstVertexValue);
        Assert.assertArrayEquals(expectWeight,weight);

    }

}
