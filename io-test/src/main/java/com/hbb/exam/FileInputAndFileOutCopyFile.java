package com.hbb.exam;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputAndFileOutCopyFile {


    @Test
    public void copy() throws IOException {
        String srcFilePath = "e:/exam.jpeg";
        String destFilePath = "e:/copy.jpeg";
        // 拷贝文件
        FileInputStream inputStream = new FileInputStream(srcFilePath);
        FileOutputStream outputStream = new FileOutputStream(destFilePath);
        // 设定一次性读取多少个字节
        byte[] bytes = new byte[1024];
        int len;
        // 不能一次性全部读取，万一文件太大，导致程序崩掉
        while ((len = inputStream.read(bytes)) != -1) {
            // 必须要根据实际长度进行写入，不然会失去精度导致文件打不开
            outputStream.write(bytes, 0, len);
        }
        // 关闭
        inputStream.close();
        outputStream.close();
    }
}
