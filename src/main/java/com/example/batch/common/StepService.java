package com.example.batch.common;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepService {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private IProcessor processor;


    public Step getStep(String prefix, int stepNo) {
        String stepName = CommonUtil.getStepName(prefix, stepNo);
        return genStep(stepName);
    }


    private Step genStep(String stepName) {
        return stepBuilderFactory.get(stepName)
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("#### Hello " + stepName);
                    if (processor != null) {
                        processor.process(stepContribution, chunkContext);
                    }
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
