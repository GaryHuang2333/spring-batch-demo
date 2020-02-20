package com.example.batch.common;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class PrintStatusProcessor implements IProcessor{
    @Override
    public void process(StepContribution stepContribution, ChunkContext chunkContext) {
        System.out.println("Step [" + chunkContext.getStepContext().getStepName() + "] execute on Thread [" + Thread.currentThread().getId() + "]");
    }
}
