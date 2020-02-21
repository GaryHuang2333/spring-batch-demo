package com.example.batch.jobListenerDemo;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Component
public class MyStepListener {
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] before step [" + stepExecution.getStepName() + "] .");
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println("#### this is [" + this.getClass().getSimpleName() + "] after step [" + stepExecution.getStepName() + "] .");
    }
}
