package com.hbb.input;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputDemo01 {

    public static void main(String[] args) {

    }


    @Test
    public void read() throws IOException {
        String filePath = "e:/hello.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //设置一次性读取多少个字节，如果不使用字节数据接收，则是一个个字节进行接收
        byte[] bytes = new byte[1024];
        int len;
        // 如果读取正常，则返回相应的字节数
        // 如果读取-1，则表示数据已经读完了
        while ((len = fileInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        //最后一定要关闭
        fileInputStream.close();

    }
}
