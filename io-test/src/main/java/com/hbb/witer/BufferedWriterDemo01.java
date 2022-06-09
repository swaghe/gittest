package com.hbb.witer;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo01 {


    @Test
    public void writer() throws IOException {

        String filePath = "e:/write.txt";
        //缓冲字符写入流 ，具有缓冲特性
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.newLine();
        writer.write("hello,world".toCharArray(), 0, 8);
        writer.newLine();
        writer.write("hbb");

        writer.close();
    }
}
