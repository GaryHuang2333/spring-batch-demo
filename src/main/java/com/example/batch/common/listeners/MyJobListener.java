package com.example.batch.common.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] before job [" + jobExecution.getJobInstance().getJobName() + "], params = " + jobExecution.getJobParameters().getParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] after job [" + jobExecution.getJobInstance().getJobName() + "], params = " + jobExecution.getJobParameters().getParameters());
    }
}
