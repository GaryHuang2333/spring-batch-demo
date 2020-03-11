package com.example.batch.readerDemo.readerRestartDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.staffDataService.IStaffDataService;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component("RestartItemStreamReader")
public class RestartItemStreamReader implements ItemStreamReader<Staff> {
    @Autowired
    @Qualifier("staffRestartService")
    private IStaffDataService staffRestartService;

    private FlatFileItemReader<Staff> reader;
    private ExecutionContext executionContext;

    public RestartItemStreamReader() {
        // set FieldSetMapper
        FieldSetMapper<Staff> fieldSetMapper = fieldSet -> Staff.builder()
                .id(fieldSet.readInt("id"))
                .age(fieldSet.readInt("age"))
                .department(fieldSet.readString("department"))
                .gender(fieldSet.readString("gender"))
                .name(fieldSet.readString("name"))
                .comment(fieldSet.readString("comment"))
                .staffNo(fieldSet.readString("staff_no")).build();

        // set DelimitedLineTokenizer
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"id", "age", "department", "gender", "name", "comment", "staff_no"});

        // set DefaultLineMapper
        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        defaultLineMapper.afterPropertiesSet();

        // set FlatFileItemReader
        FlatFileItemReader<Staff> flatFileItemReader = new FlatFileItemReader();
        flatFileItemReader.setResource(new ClassPathResource("staff/input/staff.csv"));
//        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(defaultLineMapper);

        this.reader = flatFileItemReader;
    }


    @Override
    public Staff read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("### " + this.getClass().getSimpleName() + " read ... ...");

        // TODO how to inject executionContext ???
        reader.open(this.executionContext);
        Staff staff = reader.read();
        return staff;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("### " + this.getClass().getSimpleName() + " open ... ...");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("### " + this.getClass().getSimpleName() + " update ... ...");

    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("### " + this.getClass().getSimpleName() + " close ... ...");

    }
}
