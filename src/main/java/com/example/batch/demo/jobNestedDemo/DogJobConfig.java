package com.example.batch.demo.jobNestedDemo;

import com.example.batch.common.services.StepService;
import com.example.batch.common.services.processService.PrintThreadIDProcessService;
import com.example.batch.common.utils.CommonUtil;
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

        stepService.setProcessor(new PrintThreadIDProcessService());
        return jobBuilderFactory.get(dogJobName)
                .start(stepService.genStep(eatBondStepName))
                .next(stepService.genStep(chaseCatStepName))
                .build();
    }
}
