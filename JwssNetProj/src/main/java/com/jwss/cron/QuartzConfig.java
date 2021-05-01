package com.jwss.cron;

import com.jwss.cron.synchro.TestJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail_1() {
        return JobBuilder.newJob(TestJob.class)
                .withIdentity("jobDetail_1")
                .storeDurably().build();
    }

    @Bean
    public Trigger myTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob("jobDetail_1")
                .withIdentity("myTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * */3 * * ? *"))
                .build();
    }
}
