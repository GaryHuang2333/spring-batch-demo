package com.example.batch.common.misc;

import org.springframework.classify.Classifier;

public interface BinaryClassifier<C, T> extends Classifier<C, T> {
    void setBinaryObject(T obj1, T obj2);
}
