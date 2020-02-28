package com.example.batch.demo.simpleDeciderDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


//@ComponentScan({"com.example.batch.demo.simpleDeciderDemo","com.example.batch.common"})
@ComponentScans({@ComponentScan("com.example.batch.common")})
//@ComponentScan("com.example.batch.common")
@SpringBootApplication
@EnableBatchProcessing
public class SimpleDeciderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleDeciderDemoApplication.class, args);
    }
}
