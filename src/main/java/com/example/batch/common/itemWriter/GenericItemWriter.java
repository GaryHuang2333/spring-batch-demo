package com.example.batch.common.itemWriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericItemWriter<T> implements ItemWriter {
    @Override
    public void write(List list) throws Exception {
        System.out.println("#### writer size = " + list.size());
        System.out.println("#### writer content = [" + list.toString() + "]");
    }
}
