package com.bupt.bigdata.exercise;

import java.io.*;
import java.util.*;

/**
 * Created by hujun on 2015/11/15.
 */
public class Utils {

    public static List<Vertex> getVertexsFromFile(String fileName,String regex) {
        List<Vertex> vertexList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        Set<Integer> set = new HashSet<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null){
                set.add(Integer.valueOf(line.split(regex)[0]));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Iterator<Integer> iter = set.iterator();
        int index = 0;
        while (iter.hasNext()){
            vertexList.add(new Vertex(index,iter.next()));
            index++;
        }
        return vertexList;
    }

    public static List<Edge> getEdgesFromeFile(String fileName,String regex){
        List<Edge> edgeList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null){
                String[] array = line.split(regex);

                Edge edge = new Edge(new Vertex(Integer.valueOf(array[0])-1,Integer.valueOf(array[0])),
                        new Vertex(Integer.valueOf(array[1])-1,Integer.valueOf(array[1])),
                        Integer.valueOf(array[2]));
                edgeList.add(edge);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return edgeList;
    }

}
