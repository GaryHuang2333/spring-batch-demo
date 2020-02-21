package com.example.batch.jobNestedDemo;

import com.example.batch.common.CommonUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class JobNestDemoApplicationTest {

    @Test
    public void test() {
        String applicationName = this.getClass().getSimpleName();
        String methodName = "test";
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now.toString() = " + now.toString());

        System.out.println("applicationName = " + CommonUtil.appendLocalDateTimeString(applicationName));
        System.out.println("methodName = " + CommonUtil.appendLocalDateTimeString(methodName));
    }
}