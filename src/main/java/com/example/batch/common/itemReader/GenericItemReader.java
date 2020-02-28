package com.example.batch.common.itemReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GenericItemReader<T> implements ItemReader {
    private LinkedList<T> itemList;

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (itemList.size() > 0) {
            T value = itemList.pop();
            System.out.println("#### readerDemo content = [" + value.toString() + "]");
            return value;
        } else {
            System.out.println("#### readerDemo content is empty");
            return null;
        }
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(LinkedList<T> itemList) {
        this.itemList = itemList;
    }
}
