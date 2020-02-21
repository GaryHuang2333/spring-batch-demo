package com.example.batch.jobListenerDemo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] before Job [" + jobExecution.getJobInstance().getJobName() + "]");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "]after Job [" + jobExecution.getJobInstance().getJobName() + "]");

    }
}
