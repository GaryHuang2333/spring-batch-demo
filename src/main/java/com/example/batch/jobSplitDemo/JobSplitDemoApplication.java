package com.example.batch.jobSplitDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.batch.common", "com.example.batch.jobSplitDemo"})
@SpringBootApplication
@EnableBatchProcessing
public class JobSplitDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobSplitDemoApplication.class, args);
    }
}
