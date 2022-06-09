package com.example.javaspi;

import com.example.javaprovider.provider.GlassProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.ServiceLoader;

@SpringBootApplication
public class JavaSpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpiApplication.class, args);
//        init();
    }

    public static void init() {
        ServiceLoader<GlassProvider> provide = ServiceLoader.load(GlassProvider.class);
        Iterator<GlassProvider> iterator = provide.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().make());
        }
    }

}
