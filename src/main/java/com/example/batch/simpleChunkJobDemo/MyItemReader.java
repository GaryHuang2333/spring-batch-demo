package com.example.batch.simpleChunkJobDemo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MyItemReader implements ItemReader<String> {
    private LinkedList<String> itemList;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (itemList.size() > 0) {
            String value = itemList.pop();
            System.out.println("#### reader content = [" + value + "]");
            return value;
        } else {
            System.out.println("#### reader content is empty");
            return null;
        }
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(LinkedList<String> itemList) {
        this.itemList = itemList;
    }
}
