package com.example.batch.writerDemo.writerIntoMutiDestinationDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterIntoMultiDestinationDemoConfig {
    private static final String applicationName = WriterIntoMultiDestinationDemoConfig.class.getSimpleName();
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("myGenericItemProcessor")
    private ItemProcessor myGenericItemProcessor;
    @Autowired
    @Qualifier("myFlatFileItemReader")
    private ItemReader myFlatFileItemReader;

    @Autowired
    @Qualifier("myCompositeItemWriter")
    private ItemStreamWriter<Staff> myCompositeItemWriter;

    @Autowired
    @Qualifier("myClassifierCompositeItemWriter")
    private ItemWriter<Staff> myClassifierCompositeItemWriter;

    @Autowired
    @Qualifier("myFlatFileItemWriter")
    private ItemStreamWriter<Staff> myFlatFileItemWriter;

    @Autowired
    @Qualifier("myStaxEventItemWriter")
    private ItemStreamWriter<Staff> myStaxEventItemWriter;

    //    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(step1(jobName))
                .build();
    }

    private Step step1(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 1);
        return stepBuilderFactory.get(stepName)
                .<Staff, Staff>chunk(10)
                .reader(myFlatFileItemReader)
                .processor(myGenericItemProcessor)
                .writer(myCompositeItemWriter)
                .build();
    }

    @Bean
    public Job job2() {
        String jobName = CommonUtil.getJobName(applicationName, 2);
        return jobBuilderFactory.get(jobName)
                .start(step2(jobName))
                .build();
    }

    private Step step2(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 2);
        return stepBuilderFactory.get(stepName)
                .<Staff, Staff>chunk(10)
                .reader(myFlatFileItemReader)
                .processor(myGenericItemProcessor)
                .writer(myClassifierCompositeItemWriter)
                .stream(myFlatFileItemWriter)
                .stream(myStaxEventItemWriter)
                .build();
    }
}
