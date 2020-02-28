package com.example.batch.common.itemWriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericItemWriter<T> implements ItemWriter<T> {
    @Override
    public void write(List<? extends T> list) throws Exception {
        System.out.println("#### writer size = " + list.size());
        System.out.println("#### writer content = {");
        for (T content : list) {
            System.out.println("#### writer content ====" + content.toString());
        }
        System.out.println("#### writer content = }");
    }
}
