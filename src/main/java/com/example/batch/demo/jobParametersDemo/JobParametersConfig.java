package com.example.batch.demo.jobParametersDemo;

import com.example.batch.common.listeners.MyJobListener;
import com.example.batch.common.listeners.MyStepListener;
import com.example.batch.common.services.IProcessService;
import com.example.batch.common.services.StepService;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("PrintParametersProcessService")
    private IProcessService printParametersProcessService123;
    @Autowired
    private MyJobListener myJobListener;
    @Autowired
    private MyStepListener myStepListener;

    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        stepService.setProcessor(printParametersProcessService123, myStepListener);
        return jobBuilderFactory.get(jobName)
                .start(stepService.getStep(jobName, 1))
                .listener(myJobListener)
                .build();
    }
}
