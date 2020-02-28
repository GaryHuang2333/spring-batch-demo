package com.example.batch.demo.helloWorldDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.batch.demo.helloWorldDemo", "com.example.batch.common"})
@SpringBootApplication
@EnableBatchProcessing
public class HelloWorldDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldDemoApplication.class, args);
    }

}
