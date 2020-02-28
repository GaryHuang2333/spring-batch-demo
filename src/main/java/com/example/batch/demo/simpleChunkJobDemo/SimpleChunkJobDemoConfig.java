package com.example.batch.demo.simpleChunkJobDemo;

import com.example.batch.common.itemProcessor.GenericItemProcessor;
import com.example.batch.common.itemReader.GenericItemReader;
import com.example.batch.common.itemWriter.GenericItemWriter;
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

import java.util.Arrays;
import java.util.LinkedList;

@Configuration
public class SimpleChunkJobDemoConfig {
    private static final String applicationName = "SimpleChunkJobDemo";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private GenericItemReader genericItemReader;
    @Autowired
    private GenericItemWriter genericItemWriter;
    @Autowired
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    @Qualifier("SimpleStringProcessService")
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
                .reader(reader1())
                .processor(processor1())
                .writer(writer1())
                .build();
    }

    private ItemWriter<String> writer1() {
        return genericItemWriter;
    }

    private ItemProcessor<String, String> processor1() {
        genericItemProcessor.setProcessService(simpleStringProcessService);
        return genericItemProcessor;
    }

    private ItemReader<String> reader1() {
        LinkedList<String> nameList = new LinkedList();
        nameList.addAll(Arrays.asList("Tom", "Jerry", "Jack", "Ben", "Marry", "John"));
        System.out.println("#### total content = [" + nameList + "]");
        genericItemReader.setItemList(nameList);
        return genericItemReader;
    }


}
