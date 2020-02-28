package com.example.batch.writerDemo.writerIntoMutiDestinationDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.itemProcessor.GenericItemProcessor;
import com.example.batch.common.services.IProcessService;
import com.example.batch.common.services.IStaffDataService;
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
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    @Qualifier("StaffProcessService")
    private IProcessService staffProcessService;
    @Autowired
    @Qualifier("StaffFlatFileService")
    private IStaffDataService staffFlatFileService;

    @Autowired
    @Qualifier("MyMultiItemWriter")
    private ItemStreamWriter<Staff> myMultiItemWriter;

    @Autowired
    @Qualifier("MyClassifierMultiItemWriter")
    private ItemWriter<Staff> myClassifierMultiItemWriter;

    @Autowired
    @Qualifier("MyFlatFileItemWriter")
    private ItemStreamWriter<Staff> myFlatFileItemWriter;

    @Autowired
    @Qualifier("MyXmlItemWriter")
    private ItemStreamWriter<Staff> myXmlItemWriter;

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
                .reader(reader1(stepName))
                .processor(processor1())
                .writer(myMultiItemWriter)
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
                .reader(reader1(stepName))
                .processor(processor1())
                .writer(myClassifierMultiItemWriter)
                .stream(myFlatFileItemWriter)
                .stream(myXmlItemWriter)
                .build();
    }

    private ItemProcessor<? super Staff, ? extends Staff> processor1() {
        genericItemProcessor.setProcessService(staffProcessService);
        return genericItemProcessor;
    }

    @Bean
    public ItemReader<Staff> reader1(String stepName) {
        return staffFlatFileService.getItemReader();
    }


}
