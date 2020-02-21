package com.example.batch.jobNestedDemo;

import com.example.batch.common.CommonUtil;
import com.example.batch.common.PrintThreadIDProcessor;
import com.example.batch.common.StepService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;

    @Bean
    public Job catJobBean() {
        String catJobName = CommonUtil.appendLocalDateTimeString("catJobName");
        String catchFishStepName = CommonUtil.appendLocalDateTimeString("catchFishStepName");
        String catchMouseStepName = CommonUtil.appendLocalDateTimeString("catchMouseStepName");

        stepService.setProcessor(new PrintThreadIDProcessor());
        return jobBuilderFactory.get(catJobName)
                .start(stepService.genStep(catchFishStepName))
                .next(stepService.genStep(catchMouseStepName))
                .build();
    }
}
