package com.example.batch.writerDemo.writerIntoDBDemo;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(@ComponentScan("com.example.batch.common"))
@SpringBootApplication
@EnableBatchProcessing
public class WriterIntoDBDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WriterIntoDBDemoApplication.class, args);
    }
}
