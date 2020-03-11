package com.example.batch.demo.simpleChunkJobDemo;

import com.example.batch.common.itemProcessor.GenericItemProcessor;
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
public class SimpleChunkJobDemoConfig {
    private static final String applicationName = "SimpleChunkJobDemo";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("myGenericItemWriter")
    private ItemWriter myGenericItemWriter;
    @Autowired
    @Qualifier("myGenericItemReader")
    private ItemReader myGenericItemReader;
    @Autowired
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    @Qualifier("simpleStringProcessService")
    private IProcessService simpleStringProcessService;

    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(step1(jobName))
                .build();
    }

    public Step step1(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 1);
        return stepBuilderFactory.get(stepName)
                .<String, String>chunk(3)
                .reader(myGenericItemReader)
                .processor(processor1())
                .writer(myGenericItemWriter)
                .build();
    }

    private ItemProcessor<String, String> processor1() {
        genericItemProcessor.setProcessService(simpleStringProcessService);
        return genericItemProcessor;
    }

}
