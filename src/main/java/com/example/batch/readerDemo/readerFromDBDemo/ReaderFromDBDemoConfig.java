package com.example.batch.readerDemo.readerFromDBDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.processService.IProcessService;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderFromDBDemoConfig {
    private static final String applicationName = "ReaderFromDB";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("myGenericItemWriter")
    private ItemWriter myGenericItemWriter;
    @Autowired
    @Qualifier("myGenericItemProcessor")
    private ItemProcessor myGenericItemProcessor;
    @Autowired
    @Qualifier("staffProcessService")
    private IProcessService staffProcessService;
    @Autowired
    @Qualifier("myJdbcPagingItemReader")
    private ItemReader myJdbcPagingItemReader;

    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(step1(jobName))
                .build();
    }

    private Step step1(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 1);
        return stepBuilderFactory.get(stepName)
                .<Staff, Staff>chunk(2)
                .reader(myJdbcPagingItemReader)
                .processor(myGenericItemProcessor)
                .writer(myGenericItemWriter)
                .build();
    }
}
