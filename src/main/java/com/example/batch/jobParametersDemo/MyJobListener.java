package com.example.batch.jobParametersDemo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] before step [" + jobExecution.getJobInstance().getJobName() + "], params = " + jobExecution.getJobParameters().getParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] after step [" + jobExecution.getJobInstance().getJobName() + "], params = " + jobExecution.getJobParameters().getParameters());
    }
}
