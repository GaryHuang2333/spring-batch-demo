package com.example.batch.common.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] before step [" + stepExecution.getStepName() + "], params = " + stepExecution.getJobParameters().getParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] after step [" + stepExecution.getStepName() + "], params = " + stepExecution.getJobParameters().getParameters());
        return ExitStatus.COMPLETED;
    }
}
