package com.example.batch.readerFromDBDemo;

import com.example.batch.common.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderFromDBDemoConfig {
    private static final String applicationName = "ReaderFromDB";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MyWriter myWriter;
    @Autowired
    private MyProcessor myProcessor;
    @Autowired
    private StaffDataService staffDataService;

    @Bean
    public Job job1() {
        String jobName = CommonUtil.getJobName(applicationName, 1);
        return jobBuilderFactory.get(jobName)
                .start(step1(jobName))
                .build();
    }

    private Step step1(String jobName) {
        String stepName = CommonUtil.getStepName(jobName, 1);
        return stepBuilderFactory.get(stepName)
                .<Staff, Staff>chunk(2)
                .reader(jdbcPagingItemReader())
                .processor(myProcessor)
                .writer(myWriter)
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Staff> jdbcPagingItemReader() {

        JdbcPagingItemReader<Staff> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(staffDataService.getDataSource());
        reader.setFetchSize(3);
        reader.setQueryProvider(staffDataService.getAllStaffProvider());
        reader.setRowMapper(staffDataService.getStaffRowMapper());

        return reader;
    }

}
