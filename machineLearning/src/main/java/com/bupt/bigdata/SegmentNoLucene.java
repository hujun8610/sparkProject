package com.bupt.bigdata;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;

/**
 * Created by hujun on 2016/1/2.
 */
public class SegmentNoLucene {

    public static Reader readFile(String fileName){
        File file = new File(fileName);
        InputStreamReader reader = null;
        try {
//            使用InputStreamReader(而非FileReader)可以设定编码格式，避免中文乱码导致无法分词
           reader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public static void main(String[] args) {
        String fileName = "data/example.txt";
        Reader reader = readFile(fileName);

        boolean useSmart = true;
        IKSegmenter segmenter = new IKSegmenter(reader,useSmart);

        StringBuilder builder = new StringBuilder();
        Lexeme lexeme = null;
        try {
            while ((lexeme = segmenter.next())!=null){
                builder.append(lexeme.getLexemeText() + "|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(builder.toString());
    }

}
