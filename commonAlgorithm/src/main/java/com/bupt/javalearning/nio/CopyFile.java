package com.bupt.javalearning.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hujun on 16/8/1.
 */
public class CopyFile {
    private static final int ALLOCATE_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        String infile = "data/tfidf/test1.txt";
        String outfile = "data/tfidf/test1-copy.txt";

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(ALLOCATE_SIZE);

        //buffer在使用之前clear,重设缓冲区,使他可以接受新的数据
        buffer.clear();

        //将输入通道的数据放入缓冲区
        while (fcin.read(buffer)!=-1){
            //flip方法可以将缓冲区中的数据写入另一个通道
            buffer.flip();
            //缓冲区数据进入输出通道
            fcout.write(buffer);
            //重设缓冲区,用于下次的使用
            buffer.clear();
        }
        //关闭通道及文件流
        fcout.close();
        fcin.close();
        fout.close();
        fin.close();

    }

}
