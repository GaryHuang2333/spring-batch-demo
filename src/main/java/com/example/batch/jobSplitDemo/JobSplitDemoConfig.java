package com.example.batch.jobSplitDemo;

import com.example.batch.common.CommonUtil;
import com.example.batch.common.PrintThreadIDProcessor;
import com.example.batch.common.StepService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class JobSplitDemoConfig {
    private static final String applicationName = "JobSplit";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private PrintThreadIDProcessor printStatusProcessor;
    @Autowired
    private StepService stepService;

    @Bean
    public Job job1() {
        System.out.println("----begin job1 ---------------------------------------------");
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(flow1(jobName))
                .split(new SimpleAsyncTaskExecutor()).add(flow2(jobName))
                .end()
                .build();

    }

    private Flow flow1(String jobName) {
        System.out.println("----begin flow1--------------------------");
        String flowName = CommonUtil.getFlowName(jobName, 1);
        return new FlowBuilder<Flow>(flowName)
                .start(stepService.getStep(flowName, 1))
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(flowName, 2))
                .next(stepService.getStep(flowName, 3))
                .build();
    }

    private Flow flow2(String jobName) {
        System.out.println("----begin flow2--------------------------");
        String flowName = CommonUtil.getFlowName(jobName, 2);
        return new FlowBuilder<Flow>(flowName)
                .start(stepService.getStep(flowName, 4))
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(flowName, 5))
                .next(stepService.getStep(flowName, 6))
                .build();
    }


}
