package com.example.batch.readerFromDBDemo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyProcessor implements ItemProcessor<Staff, Staff> {
    @Override
    public Staff process(Staff staff) throws Exception {
        staff.setComment(Optional.ofNullable(staff.getComment()).orElse("") + " # checked");
        return staff;
    }
}
