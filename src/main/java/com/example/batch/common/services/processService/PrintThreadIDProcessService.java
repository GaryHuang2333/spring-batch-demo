package com.example.batch.common.services.processService;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Service;

@Service("printThreadIDProcessService")
public class PrintThreadIDProcessService implements IProcessService<StepContribution, ChunkContext, Object> {


    @Override
    public Object process(StepContribution input) {
        return null;
    }

    @Override
    public Object process(StepContribution stepContribution, ChunkContext chunkContext) {
        System.out.println("#### " + this.getClass().getSimpleName() + " Step [" + chunkContext.getStepContext().getStepName() + "] execute on Thread [" + Thread.currentThread().getId() + "]");
        return null;
    }

}
