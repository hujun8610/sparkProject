package com.bupt.javalearning.graph.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujun on 2016/6/10.
 */
public class FileUtils {

    /**
     *  ���ļ��ж�ȡ���ݣ����ļ���Ϊ�ջ��ļ������ڡ��ļ�����Ϊ0��ֱ�ӷ���һ������Ϊ0��list
     *  �ļ���ʽ���£�src,des,value
     *  �ֱ��ʾ��㡢�յ�ͻ���
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
