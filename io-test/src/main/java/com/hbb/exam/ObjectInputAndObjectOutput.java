package com.hbb.exam;

import com.hbb.pojo.User;
import org.junit.Test;

import java.io.*;

public class ObjectInputAndObjectOutput {


    @Test
    public void test() throws IOException, ClassNotFoundException {
        //对象转字节数组，对象转字节数组，必须让ByteArrayOutputStream生成一个对象实例，不然调用不了toByteArray
        User user = new User("hbb", 22);
        //1.创建对象
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bo);
        //2.通过ObjectOutputStream写入到ByteArrayOutputStream，再由ByteArrayOutputStream转换字节数组
        outputStream.writeObject(user);
        byte[] finalData = bo.toByteArray();
        //3.关闭流
        outputStream.close();
        System.out.println(finalData.length);

        //字节数组转对象
        //1.将字节数组存入ByteArrayInputStream中
        ByteArrayInputStream bi = new ByteArrayInputStream(finalData);
        ObjectInputStream inputStream = new ObjectInputStream(bi);
        //2.最后将字节数组转换为对象
        User object = (User) inputStream.readObject();

        System.out.println(object.getName());
        //3.关闭流
        inputStream.close();

    }
}
