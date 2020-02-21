package com.example.batch.common;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class PrintParametersProcessor implements IProcessor {
    @Override
    public void process(StepContribution stepContribution, ChunkContext chunkContext) {
        System.out.println("#### stepContribution params = " + stepContribution.getStepExecution().getJobParameters().getParameters());
        System.out.println("#### chunkContext params = " + chunkContext.getStepContext().getJobParameters());
    }
}
