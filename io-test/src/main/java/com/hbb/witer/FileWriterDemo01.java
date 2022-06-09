package com.hbb.witer;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo01 {


    @Test
    public void writer() throws IOException {
        String filePath = "e:/write.txt";
        //文件字符写入流，写入字符到文本中。
        FileWriter writer = new FileWriter(filePath, true);

        writer.write("hello world".toCharArray(), 0, 5);
        //最后必须关闭流或者flush流，不然数据没有真正的写入
        writer.close();
    }
}
