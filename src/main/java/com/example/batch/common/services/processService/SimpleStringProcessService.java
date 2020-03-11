package com.example.batch.common.services.processService;

import org.springframework.stereotype.Service;

@Service("simpleStringProcessService")
public class SimpleStringProcessService implements IProcessService<String, Object, String> {
    @Override
    public String process(String input) {
        System.out.println("#### " + this.getClass().getSimpleName() + " input = " + input);
        return input + " checked";
    }

    @Override
    public String process(String input1, Object input2) {
        return null;
    }
}
