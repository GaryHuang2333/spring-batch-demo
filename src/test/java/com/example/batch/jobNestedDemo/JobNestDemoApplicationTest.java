package com.example.batch.jobNestedDemo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

class JobNestDemoApplicationTest {

    @Test
    public void test() {
        LinkedList<String> nameList = new LinkedList();
        nameList.addAll(Arrays.asList("Tom", "Jerry", "Jack", "Ben", "Marry", "John"));

        System.out.println("size = " + nameList.size());

        for (int i = 0; i < nameList.size();) {
            String getName = nameList.get(i);
            String popName = nameList.pop();
            System.out.println("getName=[" + getName + "], popName=[" + popName + "], ind=" + i + ", size=" + nameList.size());
        }

        System.out.println("final popName = " + nameList.pop());


    }
}