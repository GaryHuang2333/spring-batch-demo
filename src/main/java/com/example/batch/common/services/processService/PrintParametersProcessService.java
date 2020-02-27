package com.example.batch.common.services.processService;

import com.example.batch.common.services.IProcessService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Service;

@Service("PrintParametersProcessService")
public class PrintParametersProcessService implements IProcessService<StepContribution, ChunkContext, Object> {
    @Override
    public String process(StepContribution input) {
        return null;
    }

    @Override
    public Object process(StepContribution stepContribution, ChunkContext chunkContext) {
        System.out.println("#### " + this.getClass().getSimpleName() + " stepContribution params = " + stepContribution.getStepExecution().getJobParameters().getParameters());
        System.out.println("#### " + this.getClass().getSimpleName() + " chunkContext params = " + chunkContext.getStepContext().getJobParameters());
        return null;
    }

}
