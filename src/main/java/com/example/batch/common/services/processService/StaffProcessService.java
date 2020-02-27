package com.example.batch.common.services.processService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IProcessService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("StaffProcessService")
public class StaffProcessService implements IProcessService<Staff, Object, Staff> {
    @Override
    public Staff process(Staff staff) {
        staff.setComment(Optional.ofNullable(staff.getComment()).orElse("") + " # checked");
        return staff;
    }

    @Override
    public Staff process(Staff input1, Object input2) {
        return null;
    }
}
