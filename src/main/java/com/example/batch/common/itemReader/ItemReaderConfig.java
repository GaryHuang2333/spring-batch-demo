package com.example.batch.common.itemReader;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;

@Configuration
public class ItemReaderConfig {

    /**
     * Dependencies
     */
    @Autowired
    @Qualifier("staffDBService")
    private IStaffDataService staffDBService;

    @Autowired
    @Qualifier("staffXmlService")
    private IStaffDataService staffXmlService;

    @Autowired
    @Qualifier("staffFlatFileService")
    private IStaffDataService staffFlatFileService;

    @Autowired
    @Qualifier("staffMultiFileService")
    private IStaffDataService staffMultiFileService;

    @Autowired
    private GenericItemReader genericItemReader;

    /**
     * Bean reader register into spring container
     */

    @Bean("myJdbcPagingItemReader")
    public JdbcPagingItemReader myJdbcPagingItemReader() {
        return (JdbcPagingItemReader<Staff>) staffDBService.setupItemReader(new JdbcPagingItemReader());
    }

    @Bean("myStaxEventItemReader")
    public StaxEventItemReader myStaxEventItemReader() {
        return (StaxEventItemReader<Staff>) staffXmlService.setupItemReader(new StaxEventItemReader());
    }

    @Bean("myFlatFileItemReader")
    public FlatFileItemReader myFlatFileItemReader() {
        return (FlatFileItemReader<Staff>) staffFlatFileService.setupItemReader(new FlatFileItemReader());
    }

    @Bean("myMultiResourceItemReader")
    public MultiResourceItemReader myMultiResourceItemReader() {
        return (MultiResourceItemReader<Staff>) staffMultiFileService.setupItemReader(new MultiResourceItemReader());
    }

    @Bean("myGenericItemReader")
    public GenericItemReader myGenericItemReader() {
        LinkedList<String> nameList = new LinkedList();
        nameList.addAll(Arrays.asList("Tom", "Jerry", "Jack", "Ben", "Marry", "John"));
        System.out.println("#### total content = [" + nameList + "]");
        genericItemReader.setItemList(nameList);
        return genericItemReader;
    }
}
