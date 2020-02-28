package com.example.batch.common.itemWriter;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.staffDataService.StaffXmlService;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class ItemWriterConfig {
    private static final String filePath = "./src/main/resources/staff/output/";

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("MyLineAggregator")
    private LineAggregator myLineAggregator;

    @Autowired
    private StaffXmlService staffXmlService;

    @Autowired
    @Qualifier("MyClassifier")
    private Classifier<Staff, ItemStreamWriter> myClassifier;

    @Bean("MyJdbcBatchItemWriter")
    public JdbcBatchItemWriter<Staff> myJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Staff> writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into backup_staff (id, age, department, gender, name, comment, staff_no) value(:id, :age, :department, :gender, :name, :comment, :staffNo)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Staff>());
        writer.afterPropertiesSet();

        return writer;
    }

    @Bean("MyFlatFileItemWriter")
    public FlatFileItemWriter<Staff> myFlatFileItemWriter() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("staff.data");
        //String classPathResourcePath = classPathResource.getFile().getAbsolutePath();

        FileSystemResource fileSystemResource = new FileSystemResource(filePath + "staff.data");
        String fileSystemResourcePath = fileSystemResource.getFile().getAbsolutePath();
        System.out.println(">>>> FlatFile file at : " + fileSystemResourcePath);

        FlatFileItemWriter<Staff> writer = new FlatFileItemWriter();
        writer.setResource(fileSystemResource);
        writer.setLineAggregator(myLineAggregator);
        writer.afterPropertiesSet();

        return writer;
    }

    @Bean("MyXmlItemWriter")
    public StaxEventItemWriter<Staff> myXmlItemWriter() throws Exception {
        FileSystemResource fileSystemResource = new FileSystemResource(filePath + "staff.xml");
        String fileSystemResourcePath = fileSystemResource.getFile().getAbsolutePath();
        System.out.println(">>>> FlatFile file at : " + fileSystemResourcePath);

        StaxEventItemWriter<Staff> writer = new StaxEventItemWriter();
        writer.setResource(fileSystemResource);
        writer.setMarshaller(staffXmlService.getXStreamMarshaller());
        writer.setRootTagName("staffs");
        writer.afterPropertiesSet();

        return writer;
    }

    @Bean("MyMultiItemWriter")
    public CompositeItemWriter<Staff> myMultiItemWriter() throws Exception {
        CompositeItemWriter<Staff> writer = new CompositeItemWriter();
        writer.setDelegates(Arrays.asList(myXmlItemWriter(), myFlatFileItemWriter()));

        return writer;
    }

    @Bean("MyClassifierMultiItemWriter")
    public ClassifierCompositeItemWriter myClassifierMultiItemWriter() {
        ClassifierCompositeItemWriter writer = new ClassifierCompositeItemWriter();
        writer.setClassifier(myClassifier);

        return writer;
    }
}
