package com.example.batch.helloWorldDemo;


import com.example.batch.common.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * demo1, simple helloWorld
 * <p>
 * to use, open comment of @Configuration
 */
@Configuration
public class HelloWorldDemoConfig {
    private static final String applicationName = "HelloWorld";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job returnJob() {
        String jobName = CommonUtil.getJobName(applicationName, 0);
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(returnStep(jobName))
                .build();
    }

    public Step returnStep(String jobName) {
        return stepBuilderFactory.get(CommonUtil.getStepName(jobName, 0))
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                        System.out.println("Hello spring batch !!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
