package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service("StaffMultiFileService")
public class StaffMultiFileService implements IStaffDataService {
    @Autowired
    @Qualifier("StaffFlatFileService")
    private IStaffDataService staffFlatFileService;

    @Value("classpath*:/staff/input/multi_staff/staff*.csv")
    private Resource[] resources;

    @Override
    public ItemReader<Staff> getItemReader() {
        MultiResourceItemReader reader = new MultiResourceItemReader();
        reader.setDelegate((ResourceAwareItemReaderItemStream) staffFlatFileService.getItemReader());
        reader.setResources(resources);

        return reader;
    }

}
