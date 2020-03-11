package com.example.batch.common.itemProcessor;

import com.example.batch.common.services.processService.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemProcessorConfig {
    @Autowired
    private GenericItemProcessor genericItemProcessor;
    @Autowired
    @Qualifier("staffProcessService")
    private IProcessService staffProcessService;
    @Autowired
    @Qualifier("printParametersProcessService")
    private IProcessService printParametersProcessService;
    @Autowired
    @Qualifier("printThreadIDProcessService")
    private IProcessService printThreadIDProcessService;
    @Autowired
    @Qualifier("simpleStringProcessService")
    private IProcessService simpleStringProcessService;

    /**
     * Bean processor register into spring container
     */

    @Bean("myGenericItemProcessor")
    public GenericItemProcessor myGenericItemProcessor() {
        genericItemProcessor.setProcessService(staffProcessService);
        return genericItemProcessor;
    }

    @Bean("myPrintParametersProcessor")
    public GenericItemProcessor myPrintParametersProcessor() {
        genericItemProcessor.setProcessService(printParametersProcessService);
        return genericItemProcessor;
    }

    @Bean("myPrintThreadIDProcessor")
    public GenericItemProcessor myPrintThreadIDProcessor() {
        genericItemProcessor.setProcessService(printThreadIDProcessService);
        return genericItemProcessor;
    }

    @Bean("mySimpleStringProcessor")
    public GenericItemProcessor mySimpleStringProcessor() {
        genericItemProcessor.setProcessService(simpleStringProcessService);
        return genericItemProcessor;
    }
}
