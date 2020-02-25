package com.example.batch.jobParametersDemo;

import com.example.batch.common.CommonUtil;
import com.example.batch.common.PrintParametersProcessor;
import com.example.batch.common.StepService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobParametersConfig {
    private static final String applicationName = JobParametersConfig.class.getSimpleName();

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;
    @Autowired
    private PrintParametersProcessor printParametersProcessor;
    @Autowired
    private MyJobListener myJobListener;
    @Autowired
    private MyStepListener myStepListener;

    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        stepService.setProcessor(printParametersProcessor, myStepListener);
        return jobBuilderFactory.get(jobName)
                .start(stepService.getStep(jobName, 1))
                .listener(myJobListener)
                .build();
    }
}
