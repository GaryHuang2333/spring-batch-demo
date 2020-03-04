package com.example.batch.common.itemWriter;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.staffDataService.StaffXmlService;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
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

    /**
     * Dependencies
     */

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("MyLineAggregator")
    private LineAggregator myLineAggregator;

    @Autowired
    private StaffXmlService staffXmlService;

    @Autowired
    private GenericItemWriter genericItemWriter;

    @Autowired
    @Qualifier("MyClassifier")
    private Classifier<Staff, ItemStreamWriter> myClassifier;


    /**
     * Bean Writer register into spring container
     */

    // JDBC item writer
    @Bean("myJdbcBatchItemWriter")
    public JdbcBatchItemWriter<Staff> myJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Staff> writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into backup_staff (id, age, department, gender, name, comment, staff_no) value(:id, :age, :department, :gender, :name, :comment, :staffNo)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Staff>());
        writer.afterPropertiesSet();

        return writer;
    }

    // Flat file item writer
    @Bean("myFlatFileItemWriter")
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

    // Xml item writer
    @Bean("myStaxEventItemWriter")
    public StaxEventItemWriter<Staff> myStaxEventItemWriter() throws Exception {
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

    // Multiple items Item writer
    @Bean("myCompositeItemWriter")
    public CompositeItemWriter<Staff> myCompositeItemWriter() throws Exception {
        CompositeItemWriter<Staff> writer = new CompositeItemWriter();
        writer.setDelegates(Arrays.asList(myStaxEventItemWriter(), myFlatFileItemWriter()));

        return writer;
    }

    // Multiple items Item writer with classifier
    @Bean("myClassifierCompositeItemWriter")
    public ClassifierCompositeItemWriter myClassifierCompositeItemWriter() {
        ClassifierCompositeItemWriter writer = new ClassifierCompositeItemWriter();
        writer.setClassifier(myClassifier);

        return writer;
    }

    // most common item writer
    @Bean("myGenericItemWriter")
    public ItemWriter myGenericItemWriter() {
        return genericItemWriter;
    }
}
