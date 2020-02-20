package com.example.batch.flowFlowDemo;

import com.example.batch.common.CommonUtil;
import com.example.batch.common.StepService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowFlowDemoConfig {
    private static final String applicationName = "FLowFlow";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;

    @Bean
    public Job job1() {
        System.out.println("----begin job1--------------------------");
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(flow1(jobName))
                .next(flow2(jobName))
                .end()
                .build();
    }

    @Bean
    public Job job2() {
        String jobName = CommonUtil.getJobName(applicationName, 2);
        System.out.println("----begin job2--------------------------");
        return jobBuilderFactory.get(jobName)
                .start(flow3(jobName))
                .end()
                .build();
    }

    public Flow flow1(String jobName) {
        System.out.println("----begin flow1--------------------------");
        String flowName = CommonUtil.getFlowName(jobName, 1);
        return new FlowBuilder<Flow>(flowName)
                .start(stepService.getStep(flowName, 1))
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(flowName, 2))
                .next(stepService.getStep(flowName, 3))
                .build();
    }

    public Flow flow2(String jobName) {
        System.out.println("----begin flow2--------------------------");
        String flowName = CommonUtil.getFlowName(jobName, 2);
        return new FlowBuilder<Flow>(flowName)
                .start(stepService.getStep(flowName, 4))
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(flowName, 3))
                .next(stepService.getStep(flowName, 2))
                .build();
    }

    public Flow flow3(String jobName) {
        System.out.println("----begin flow3--------------------------");
        String flowName = CommonUtil.getFlowName(jobName, 3);
        return new FlowBuilder<Flow>(flowName)
//        return flowFlowBuilder
                .start(stepService.getStep(flowName, 1))
                .next(stepService.getStep(flowName, 3))
                .end();
    }
}
