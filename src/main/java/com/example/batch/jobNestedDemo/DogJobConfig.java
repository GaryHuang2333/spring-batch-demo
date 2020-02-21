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
public class DogJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepService stepService;

    @Bean("dogJobBean")
    public Job dogJobBean() {
        String dogJobName = CommonUtil.appendLocalDateTimeString("dogJobName");
        String eatBondStepName = CommonUtil.appendLocalDateTimeString("eatBondStepName");
        String chaseCatStepName = CommonUtil.appendLocalDateTimeString("chaseCatStepName");

        stepService.setProcessor(new PrintThreadIDProcessor());
        return jobBuilderFactory.get(dogJobName)
                .start(stepService.genStep(eatBondStepName))
                .next(stepService.genStep(chaseCatStepName))
                .build();
    }
}
