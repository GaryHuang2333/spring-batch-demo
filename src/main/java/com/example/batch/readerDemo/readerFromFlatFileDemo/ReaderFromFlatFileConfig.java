package com.example.batch.readerDemo.readerFromFlatFileDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.itemProcessor.GenericItemProcessor;
import com.example.batch.common.services.IProcessService;
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
public class ReaderFromFlatFileConfig {
    private static final String applicationName = "ReaderFromFlatFile";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    @Qualifier("myGenericItemWriter")
    private ItemWriter myGenericItemWriter;
    @Autowired
    @Qualifier("StaffProcessService")
    private IProcessService staffProcessService;
    @Autowired
    @Qualifier("myFlatFileItemReader")
    private ItemReader myFlatFileItemReader;

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
                .<Staff, Staff>chunk(3)
                .reader(myFlatFileItemReader)
                .processor(processor1())
                .writer(myGenericItemWriter)
                .build();
    }

    private ItemProcessor<? super Staff, ? extends Staff> processor1() {
        genericItemProcessor.setProcessService(staffProcessService);
        return genericItemProcessor;
    }
}
