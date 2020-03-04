package com.example.batch.common.services.staffDataService;

import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;

@Service("staffRestartService")
public class StaffRestartService implements IStaffDataService {
    @Override
    public ItemReader setupItemReader(ItemReader itemReader) {
        return null;
    }
}
