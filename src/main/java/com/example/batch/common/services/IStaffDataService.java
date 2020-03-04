package com.example.batch.common.services;

import org.springframework.batch.item.ItemReader;

public interface IStaffDataService {
    ItemReader setupItemReader(ItemReader itemReader);
}
