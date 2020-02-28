package com.example.batch.demo.jobFlowDemo;


import com.example.batch.common.services.StepService;
import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * demo2, simple demo of step flow
 */
@Configuration
public class JobFlowDemo1Config {
    private static final String applicationName = "JobFlow";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;

    @Bean
    public Job job1() {
        System.out.println("----begin job1 ---------------------------------------------");
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(stepService.getStep(jobName, 1))
                .next(stepService.getStep(jobName, 2))
                .next(stepService.getStep(jobName, 3))
                .build();
    }

    @Bean
    public Job job2() {
        System.out.println("----begin job2 ---------------------------------------------");
        String jobName = CommonUtil.getJobName(applicationName, 2);
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(stepService.getStep(jobName, 1))
                .on("COMPLETED").to(stepService.getStep(jobName, 3))
                .from(stepService.getStep(jobName, 3)).on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(jobName, 4))
                .end().build();
    }

    @Bean
    public Job job3() {
        System.out.println("----begin job3 ---------------------------------------------");
        String jobName = CommonUtil.getJobName(applicationName, 3);
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(stepService.getStep(jobName, 1))
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(jobName, 3))
                .on(ExitStatus.FAILED.getExitCode()).to(stepService.getStep(jobName, 2))
                .from(stepService.getStep(jobName, 2)).on(ExitStatus.COMPLETED.getExitCode()).to(stepService.getStep(jobName, 4))
                .end().build();
    }
}
