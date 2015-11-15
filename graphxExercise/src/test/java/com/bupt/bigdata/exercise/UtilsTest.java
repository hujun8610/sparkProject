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
        System.out.println(fileName);
        List<Vertex> list = Utils.getVertexsFromFile(fileName,regex);
        Assert.assertEquals(list.size(),6);
    }

    @Test
    public void  testGetEdgesFromeFile(){
        List<Edge> list = Utils.getEdgesFromeFile(fileName,regex);
        Assert.assertEquals(list.size(),10);
    }

}
