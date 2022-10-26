package com.example.springbatch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloConfiguration {

    /*
     하나만 실행하고자 할때
     Edit configurations -> programs arguments  안보일 경우 modify options 에서 체크
     --job.names=Job이름
     --job.names=helloJob
     */
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloJob() {                                 //Job batch의 실행 단위
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())     //RunIdIncrementer : 항상 jobInstance 생성 run.id 시퀀스 자동 생성
                .start(this.helloStep())
                .build();
    }

    @Bean
    public Step helloStep() {                           //step은 job의 실행 단위  하나의 job은 하나 이상의 step을 가질수 있음
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }


}
