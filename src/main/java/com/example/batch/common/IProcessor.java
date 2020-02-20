package com.example.batch.common;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public interface IProcessor {
    void process(StepContribution stepContribution, ChunkContext chunkContext);
}
