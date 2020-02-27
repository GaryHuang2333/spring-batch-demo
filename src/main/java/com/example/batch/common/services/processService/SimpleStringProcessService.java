package com.example.batch.common.services.processService;

import com.example.batch.common.services.IProcessService;
import org.springframework.stereotype.Service;

@Service("SimpleStringProcessService")
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
