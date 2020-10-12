package com.jicg.jman.config;

import com.jicg.jman.job.TestJob1;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jicg on 2020/6/26
 */

/**
 * Quartz的相关配置，注册JobDetail和Trigger
 * 注意JobDetail和Trigger是org.quartz包下的，不是spring包下的，不要导入错误
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(TestJob1.class)
                .withIdentity("job", "job_group")
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger trigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("job", "job_group")
                .startNow()
                // 每天0点执行
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        return trigger;
    }
//
//
//    @Bean
//    public JobDetail jobDetail2() {
//        JobDetail jobDetail = JobBuilder.newJob(QuartzJob2.class)
//                .withIdentity("job2", "job_group2")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    @Bean
//    public Trigger trigger2() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(jobDetail())
//                .withIdentity("job2", "job_group2")
//                .startNow()
//                // 每天0点执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//                .build();
//        return trigger;
//    }
}
