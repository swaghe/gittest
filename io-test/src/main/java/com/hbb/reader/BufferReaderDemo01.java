package com.hbb.reader;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderDemo01 {

    @Test
    public void read() throws IOException {
        String filePath = "e:/read.txt";

        //包装流 简单来说就是包裹核心流，使核心流具有更多的功能
        //核心流 简单来说就是具有直接操作数据源的流
        //Buffered... 就是包装流，包装流必须引入核心流才能正常使用
        //File... 就是核心流
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        int len;
        char[] data = new char[1024];
        //按字符数组进行读取
        while ((len = reader.read(data)) != -1) {
            System.out.print(new String(data, 0, len));
        }
        //关闭包装流，就相当于关闭核心流
        reader.close();
    }
}
