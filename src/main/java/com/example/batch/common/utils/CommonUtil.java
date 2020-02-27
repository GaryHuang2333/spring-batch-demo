package com.example.batch.common.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class CommonUtil {
    private static final int NAME_MAX_SIZE = 100;
    private static String now = LocalDateTime.now().toString();

    public static String appendLocalDateTimeString(String content) {
        return content + "_[" + now + "]";
    }

    /**
     * default dateInd = true
     *
     * @param applicationName
     * @param jobNo
     * @return
     */
    public static String getJobName(String applicationName, int jobNo) {
        return getJobName(applicationName, jobNo, true);
    }

    public static String getJobName(String applicationName, int jobNo, boolean dateInd) {
        if (dateInd) {
            return applicationName + "_job_" + Integer.toString(jobNo) + "_[" + now + "]";

        } else {
            return applicationName + "_job_" + Integer.toString(jobNo);
        }
    }

    /**
     * default dateInd = true
     *
     * @param prefix
     * @param stepNo
     * @return
     */
    public static String getStepName(String prefix, int stepNo) {
        return getStepName(prefix, stepNo, true);
    }

    public static String getStepName(String prefix, int stepNo, boolean dateInd) {
        if (dateInd) {
            return prefix + "_step_" + Integer.toString(stepNo) + "_[" + now + "]";
        } else {
            return prefix + "_step_" + Integer.toString(stepNo);
        }
    }

    public static String getFlowName(String jobName, int flowNo) {
        return jobName + "_flow_" + Integer.toString(flowNo) + "_[" + now + "]";
    }

    public static String trimNameSize(String name) {
        if (!StringUtils.isEmpty(name) && name.length() > NAME_MAX_SIZE) {
            String substring = name.substring(0, 99);
            System.out.println("#### name is to long : Original [" + name.length() + "] " + name);
            System.out.println("#### name is to long : After trim [" + substring.length() + "] " + substring);
            return substring;
        } else {
            return name;
        }
    }
}
