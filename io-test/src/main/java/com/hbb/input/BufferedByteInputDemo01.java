package com.hbb.input;

import org.junit.Test;

import java.io.*;
import java.math.BigInteger;

public class BufferedByteInputDemo01 {


    @Test
    public void read() throws IOException {
        String filePath = "e:/demo.jpeg";
        //读取图片文件，然后转为字节数组
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
        //ByteArrayOutputStream 每次write都会扩容内部的buf数组，使之数组容量变大。
        //toByteArray 将最终的buf字节数组返回
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        byte[] finalData = outputStream.toByteArray();
        System.out.println(finalData.length / 1024);
        inputStream.close();
        outputStream.close();

        //总结
        //如果你需要从文件读取到程序就需要用File，如果你需要从字节数组读取到程序就需要用ByteArray
        //如果你需要将数据写入到文件中，就要用File。如果你需要将数据写入到字节数组中，就要用ByteArray

        BufferedInputStream stream = new BufferedInputStream(new ByteArrayInputStream(finalData));
        FileOutputStream fileOutputStream = new FileOutputStream("e:/copy.jpeg");

        byte[] readData = new byte[1024];
        int copyLen;

        while ((copyLen = stream.read(readData)) != -1) {
            fileOutputStream.write(readData, 0, copyLen);
        }
        System.out.println("复制完成");
        stream.close();
        fileOutputStream.close();

    }
}
