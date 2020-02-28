package com.example.batch.demo.simpleDeciderDemo;

import com.example.batch.common.services.StepService;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleDeciderDemoConfig {
    private static final String applicationName = "SimpleDecider";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;


    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(stepService.getStep(jobName, 0))
                .next(decider1())
                .from(decider1()).on("0").to(stepService.getStep(jobName, 0))
                .from(decider1()).on("1").to(stepService.getStep(jobName, 1))
                .from(decider1()).on("2").to(stepService.getStep(jobName, 2))
                .end()
                .build();
    }


    private JobExecutionDecider decider1() {
        return (jobExecution, stepExecution) -> {
            int random = Double.valueOf(Math.random() * 10).intValue();
            int result = random % 3;
            System.out.println("#### random = " + random + "; random % 3 = " + result);
            return new FlowExecutionStatus(Integer.toString(result));
        };
    }

    private JobExecutionDecider decider2() {
        return new JobExecutionDecider() {
            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
                int random = Double.valueOf(Math.random() * 10).intValue();
                int result = random % 3;
                System.out.println("#### random = " + random + "; random % 3 = " + result);
                return new FlowExecutionStatus(Integer.toString(result));
            }
        };
    }


}
