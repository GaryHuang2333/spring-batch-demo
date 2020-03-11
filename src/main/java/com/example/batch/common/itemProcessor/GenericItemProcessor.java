package com.example.batch.common.itemProcessor;

import com.example.batch.common.services.processService.IProcessService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GenericItemProcessor<I, O> implements ItemProcessor<I, O> {
    private IProcessService<I, Object, O> processService;

    public O process(I input) throws Exception {
        System.out.println("#### GenericItemProcessor content = [" + input.toString() + "]");
        O output = processService.process(input);
        return output;
    }

    public IProcessService<I, Object, O> getProcessService() {
        return processService;
    }

    public void setProcessService(IProcessService<I, Object, O> processService) {
        this.processService = processService;
    }
}
