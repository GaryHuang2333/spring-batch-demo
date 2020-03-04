package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service("staffFlatFileService")
public class StaffFlatFileService implements IStaffDataService {

    @Override
    public ItemReader setupItemReader(ItemReader itemReader) {
        if (itemReader instanceof FlatFileItemReader) {
            FlatFileItemReader flatFileItemReader = (FlatFileItemReader) itemReader;
            flatFileItemReader.setResource(new ClassPathResource("staff/input/staff.csv"));
            flatFileItemReader.setLinesToSkip(1);
            flatFileItemReader.setLineMapper(getStaffLineMapper());
            itemReader = flatFileItemReader;
        }

        return itemReader;
    }

    private LineMapper<Staff> getStaffLineMapper() {
        DefaultLineMapper mapper = new DefaultLineMapper();
        mapper.setLineTokenizer(getDelimitedLineTokenizer());
        mapper.setFieldSetMapper(getFieldSetMapper());
        mapper.afterPropertiesSet();
        return mapper;
    }

    private FieldSetMapper<Staff> getFieldSetMapper() {
        return fieldSet -> Staff.builder()
                .id(fieldSet.readInt("id"))
                .age(fieldSet.readInt("age"))
                .department(fieldSet.readString("department"))
                .gender(fieldSet.readString("gender"))
                .name(fieldSet.readString("name"))
                .comment(fieldSet.readString("comment"))
                .staffNo(fieldSet.readString("staff_no")).build();
    }

    private DelimitedLineTokenizer getDelimitedLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "age", "department", "gender", "name", "comment", "staff_no"});
        return tokenizer;
    }

}
