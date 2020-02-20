package com.example.batch.flowFlowDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.batch.flowFlowDemo", "com.example.batch.common"})
@SpringBootApplication
@EnableBatchProcessing
public class FlowFlowDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowFlowDemoApplication.class, args);
    }
}

