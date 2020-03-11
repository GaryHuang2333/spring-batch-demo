package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("staffXmlService")
public class StaffXmlService implements IStaffDataService {

    @Override
    public ItemReader setupItemReader(ItemReader itemReader) {
        if (itemReader instanceof StaxEventItemReader) {
            StaxEventItemReader staxEventItemReader = (StaxEventItemReader) itemReader;
            staxEventItemReader.setResource(new ClassPathResource("staff/input/staff.xml"));
            staxEventItemReader.setFragmentRootElementName("staff");
            staxEventItemReader.setUnmarshaller(getXStreamMarshaller());
            itemReader = staxEventItemReader;
        }

        return itemReader;
    }

    public XStreamMarshaller getXStreamMarshaller() {
        Map<String, Class> aliasesMap = new HashMap<>();
        aliasesMap.put("staff", Staff.class);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliasesMap);

        return marshaller;
    }
}
