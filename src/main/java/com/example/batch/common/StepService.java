package com.example.batch.common;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepService {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //    @Autowired
    private IProcessor processor;
    //    @Autowired
    private StepExecutionListener stepListener;

    public void setProcessor(IProcessor processor) {
        this.processor = processor;
    }

    public void setProcessor(IProcessor processor, StepExecutionListener stepListener) {
        this.processor = processor;
        this.stepListener = stepListener;
    }

    public Step getStep(String prefix, int stepNo, boolean dateInd) {
        String stepName = CommonUtil.getStepName(prefix, stepNo, dateInd);
        return genStep(stepName);
    }

    public Step getStep(String prefix, int stepNo) {
        return getStep(prefix, stepNo, true);
    }

    public Step genStep(String stepName) {
        TaskletStep step;
        TaskletStepBuilder tasklet = stepBuilderFactory.get(stepName)
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("#### Hello " + stepName);
                    if (processor != null) {
                        processor.process(stepContribution, chunkContext);
                    }
                    return RepeatStatus.FINISHED;
                });

        if (stepListener != null) {
            step = tasklet.listener(stepListener).build();
        } else {
            step = tasklet.build();
        }

        return step;
    }
}
