package com.example.batch.jobFlowDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.batch.jobFlowDemo", "com.example.batch.common"})
@SpringBootApplication
@EnableBatchProcessing
public class JobFlowDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(JobFlowDemo1Application.class, args);
    }

}
