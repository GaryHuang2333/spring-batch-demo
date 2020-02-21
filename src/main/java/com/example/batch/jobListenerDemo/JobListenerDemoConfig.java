package com.example.batch.jobListenerDemo;

import com.example.batch.common.CommonUtil;
import com.example.batch.common.IProcessor;
import com.example.batch.common.StepService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobListenerDemoConfig {
    private static final String applicationName = JobListenerDemoConfig.class.getSimpleName();

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobExecutionListener myJobListener;
    @Autowired
    private MyStepListener myStepListener;
    @Autowired
    private StepService stepService;
    @Autowired
    private IProcessor printThreadIDProcessor;


    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(stepService.getStep(jobName, 1))
                .listener(myJobListener)
                .build();
    }

    @Bean
    public Job job2() {
        String jobName = CommonUtil.getJobName(applicationName, 2);
        return jobBuilderFactory.get(jobName)
                .start(step1(jobName))
                .listener(myJobListener)
                .build();
    }

    private Step step1(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 1);
        return stepBuilderFactory.get(stepName)
                .tasklet((stepContribution, chunkContext) -> {
                    printThreadIDProcessor.process(stepContribution, chunkContext);
                    return RepeatStatus.FINISHED;
                })
                .listener(myStepListener)
                .build();
    }
}
