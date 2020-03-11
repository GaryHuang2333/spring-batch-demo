package com.example.batch.common.services.processService;

public interface IProcessService<I, T, O> {
    O process(I input);

    O process(I input1, T input2);
}
