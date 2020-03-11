package com.example.batch.common.services.staffDataService;

import org.springframework.batch.item.ItemReader;

public interface IStaffDataService {
    ItemReader setupItemReader(ItemReader itemReader);
}
