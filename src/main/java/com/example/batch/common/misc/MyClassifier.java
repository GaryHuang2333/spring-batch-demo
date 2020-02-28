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
    @Qualifier("MyFlatFileItemWriter")
    private ItemStreamWriter<Staff> myFlatFileItemWriter;

    @Autowired
    @Qualifier("MyXmlItemWriter")
    private ItemStreamWriter<Staff> myXmlItemWriter;

    @Override
    public ItemStreamWriter classify(Staff staff) {
        ItemStreamWriter writer = staff.getId() % 2 == 0 ? myFlatFileItemWriter : myXmlItemWriter;
        return writer;
    }
}
