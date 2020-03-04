package com.example.batch.common.misc;

import com.example.batch.common.entities.Staff;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

@Component("MyClassifier")
public class MyClassifier implements Classifier<Staff, ItemStreamWriter> {
    @Autowired
    @Qualifier("myFlatFileItemWriter")
    private ItemStreamWriter<Staff> myFlatFileItemWriter;

    @Autowired
    @Qualifier("myStaxEventItemWriter")
    private ItemStreamWriter<Staff> myStaxEventItemWriter;

    @Override
    public ItemStreamWriter classify(Staff staff) {
        ItemStreamWriter writer = staff.getId() % 2 == 0 ? myFlatFileItemWriter : myStaxEventItemWriter;
        return writer;
    }
}
