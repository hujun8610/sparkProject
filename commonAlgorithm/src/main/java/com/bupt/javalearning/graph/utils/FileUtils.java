package com.bupt.javalearning.graph.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujun on 2016/6/10.
 */
public class FileUtils {

    /**
     *  从文件中读取数据，若文件名为空或文件不存在、文件长度为0则直接返回一个长度为0的list
     *  文件格式如下：src,des,value
     *  分别表示起点、终点和弧长
     *  eg: 1,2,5
     *      2,3,6
     *      4,5,8
     * */
    public static List<String> read(String fileName){
        List<String> list = new ArrayList<String>();

        if(fileName == null) return list;
        File file = new File(fileName);
        if(!file.exists()) return list;
        BufferedReader bufReader = null;

        try {
            bufReader = new BufferedReader(new FileReader(file));
            String tmp = bufReader.readLine();
            while (tmp!=null){
                list.add(tmp);
                tmp = bufReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }



}
