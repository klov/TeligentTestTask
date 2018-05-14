package com.mytest.teligen.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.mytest.teligen")
public class Main {

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
