package com.example.batch.common.services.staffDataService;

import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service("staffMultiFileService")
public class StaffMultiFileService implements IStaffDataService {
    @Autowired
    @Qualifier("staffFlatFileService")
    private IStaffDataService staffFlatFileService;

    @Value("classpath*:/staff/input/multi_staff/staff*.csv")
    private Resource[] resources;

    @Override
    public ItemReader setupItemReader(ItemReader itemReader) {
        if (itemReader instanceof MultiResourceItemReader) {
            ItemReader myFlatFileItemReader = staffFlatFileService.setupItemReader(new FlatFileItemReader());
            MultiResourceItemReader multiResourceItemReader = (MultiResourceItemReader) itemReader;
            multiResourceItemReader.setDelegate((ResourceAwareItemReaderItemStream) myFlatFileItemReader);
            multiResourceItemReader.setResources(resources);
            itemReader = multiResourceItemReader;
        }

        return itemReader;
    }

}
