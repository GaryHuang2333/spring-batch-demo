package com.example.batch.demo.jobNestedDemo;

import com.example.batch.common.utils.CommonUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * to start the animalJob, add config inth properties : spring.batch.job.names=animalJobName
 */
@Configuration
public class AnimalJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    @Qualifier("catJobBean")
    private Job catJob;
    @Autowired
    @Qualifier("dogJobBean")
    private Job dogJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job animalJobBean(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        String animalJobName = "animalJobName";
        return jobBuilderFactory.get(animalJobName)
                .start(catStep(jobRepository, platformTransactionManager))
                .next(dogStep(jobRepository, platformTransactionManager))
                .build();
    }

    private Step catStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        String catStepName = CommonUtil.appendLocalDateTimeString("catStepName");
        return new JobStepBuilder(new StepBuilder(catStepName))
                .job(catJob)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    private Step dogStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        String dogStepName = CommonUtil.appendLocalDateTimeString("dogStepName");
        return new JobStepBuilder(new StepBuilder(dogStepName))
                .job(dogJob)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }
}
