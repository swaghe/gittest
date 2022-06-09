package com.reflex.demo01;

public class Demo01 {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class string = String.class;

        System.out.println(string.getPackage());
        System.out.println(string.getName());
        System.out.println(string.getSimpleName());
        System.out.println(string.getInterfaces());
        System.out.println(string.getAnnotations());
        System.out.println(string.isEnum());

        String o = (String) string.newInstance();
        System.out.println(o);

        Class<String> s = (Class<String>) Class.forName("java.lang.String");
        String s1 = s.newInstance();
        s1 = "ssss";
        System.out.println(s1);

        System.out.println(s.getPackage());
        System.out.println(s.getName());
        System.out.println(s.getSimpleName());
        System.out.println(s.getInterfaces());
        System.out.println(s.getAnnotations());
        System.out.println(s.isEnum());

    }
}
