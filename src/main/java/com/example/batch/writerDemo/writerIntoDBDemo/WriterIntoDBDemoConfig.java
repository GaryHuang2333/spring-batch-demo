package com.example.batch.writerDemo.writerIntoDBDemo;

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
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterIntoDBDemoConfig {
    private static final String applicationName = WriterIntoDBDemoConfig.class.getSimpleName();
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
    @Qualifier("MyJdbcBatchItemWriter")
    private ItemWriter<Staff> myJdbcBatchItemWriter;

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
                .<Staff, Staff>chunk(10)
                .reader(reader1(stepName))
                .processor(processor1())
                .writer(myJdbcBatchItemWriter)
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
