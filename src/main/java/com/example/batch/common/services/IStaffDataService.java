package com.example.batch.common.services;

import com.example.batch.common.entities.Staff;
import org.springframework.batch.item.ItemReader;

public interface IStaffDataService {
    ItemReader<Staff> getItemReader();
}
