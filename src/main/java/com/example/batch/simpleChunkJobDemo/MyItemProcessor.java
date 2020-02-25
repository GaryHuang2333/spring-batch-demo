package com.example.batch.simpleChunkJobDemo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String s) throws Exception {
        System.out.println("#### processor content = [" + s + "]");
        return s;
    }
}
