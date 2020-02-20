package com.example.batch.common;

public class CommonUtil {
    public static String getJobName(String applicationName, int jobNo) {
        return applicationName +"_job_"+ Integer.toString(jobNo) + "_[" + Double.valueOf(Math.random() * 100).intValue() + "]";
    }

    public static String getStepName(String prefix, int stepNo){
        return prefix + "_step_" + Integer.toString(stepNo) + "_[" + Double.valueOf(Math.random() * 100).intValue() + "]";
    }

    public static String getFlowName(String jobName, int flowNo){
        return jobName + "_flow_" + Integer.toString(flowNo) + "_[" + Double.valueOf(Math.random() * 100).intValue() + "]";
    }
}
