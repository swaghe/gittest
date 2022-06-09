package com.hbb.reader;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo01 {


    @Test
    public void read() throws IOException {
        String filePath = "e:/read.txt";
        //文件字符读取流，通过字符数组进行读取，最好就是操作文本，不能操作二进制数据。
        FileReader reader = new FileReader(filePath);
        int len;
        char[] data = new char[1024];
        //如果返回-1，则说明数据已经全部读取完毕，反之将读取的字符存入字符数组中
        while ((len = reader.read(data)) != -1) {
            System.out.print(new String(data));
        }
        //关闭
        reader.close();
    }
}
