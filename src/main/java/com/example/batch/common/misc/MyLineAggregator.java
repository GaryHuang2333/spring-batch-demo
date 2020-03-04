package com.example.batch.common.misc;

import com.example.batch.common.entities.Staff;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.stereotype.Component;

@Component("MyLineAggregator")
public class MyLineAggregator implements LineAggregator<Staff> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String aggregate(Staff staff) {
        String value = null;
        try {
            value = objectMapper.writeValueAsString(staff);
        } catch (JsonProcessingException e) {
            System.out.println("unable to parse as String");
            e.printStackTrace();
        }
        return value;
    }
}
