package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("StaffXmlService")
public class StaffXmlService implements IStaffDataService {
    @Override
    public ItemReader<Staff> getItemReader() {
        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(new ClassPathResource("staff/staff.xml"));
        reader.setFragmentRootElementName("staff");
        reader.setUnmarshaller(getUnmarshaller());
        return reader;
    }

    private XStreamMarshaller getUnmarshaller() {
        Map<String, Class > aliasesMap = new HashMap<>();
        aliasesMap.put("staff", Staff.class);

        XStreamMarshaller marshaller  = new XStreamMarshaller();
        marshaller.setAliases(aliasesMap);

        return marshaller;
    }
}
