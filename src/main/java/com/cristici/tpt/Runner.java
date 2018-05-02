package com.cristici.tpt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.MalformedURLException;

public class Runner {

    public static void run(ClassLoader classLoader) throws MalformedURLException, ClassNotFoundException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.setClassLoader(classLoader);
        ctx.register(SpringBoot.class);
        ctx.refresh();
    }
}
