package com.example.batch.readerDemo.readerRestartDemo;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.itemProcessor.GenericItemProcessor;
import com.example.batch.common.itemWriter.GenericItemWriter;
import com.example.batch.common.services.IProcessService;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderRestartDemoConfig {
    private static final String applicationName = "ReaderRestart";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    private GenericItemWriter genericItemWriter;
    @Autowired
    @Qualifier("StaffProcessService")
    private IProcessService staffProcessService;
    @Autowired
    private RestartItemStreamReader restartItemStreamReader;


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
                .reader(restartItemStreamReader)
                .processor(processor1())
                .writer(genericItemWriter)
                .build();
    }

    private ItemProcessor<? super Staff, ? extends Staff> processor1() {
        genericItemProcessor.setProcessService(staffProcessService);
        return genericItemProcessor;

    }
}
