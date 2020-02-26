package com.example.batch.readerFromDBDemo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyWriter implements ItemWriter<Staff> {
    @Override
    public void write(List<? extends Staff> list) throws Exception {
        int size = list.size();
        String content = list.stream().map(staff -> ((Staff) staff).toString()).collect(Collectors.joining(";\n"));
        System.out.println("#### writer size = " + size);
        System.out.println("#### writer size content = {" + content + "}");
    }
}
