package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("StaffRestartService")
public class StaffRestartService implements IStaffDataService {
    @Autowired
    @Qualifier("StaffFlatFileService")
    private IStaffDataService staffFlatFileService;

    @Override
    public ItemReader<Staff> getItemReader() {
        return staffFlatFileService.getItemReader();
    }
}
