package com.hbb.output;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIleOutputDemo01 {

    public static void main(String[] args) {

    }

    @Test
    public void write() throws IOException {

        String filePath = "e:/write.txt";
        // 1.FileOutputStream，如果不想覆盖前者内容的话可以，在第二个参数，填写true，就是追加到文本后面。
        FileOutputStream outputStream = new FileOutputStream(filePath);
        String str = "i am a  hbb";
        outputStream.write(str.getBytes());
        outputStream.close();

    }
}
