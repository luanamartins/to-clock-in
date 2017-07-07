package org.clock.in.configuration;

import org.clock.in.jobs.WorkTimeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"org.clock.in.jobs", "org.clock.in.jobs.registers"})
public class JobConfiguration {

    @Autowired
    private WorkTimeJob jobLauncher;

//    @Bean
//    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
//        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
//        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
//        return jobRegistryBeanPostProcessor;
//    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(WorkTimeJob.class);
        Map map = new HashMap();
        map.put("jobName", "fxmarket_prices_etl_job");
        map.put("jobLauncher", jobLauncher);
        factory.setJobDataAsMap(map);
        factory.setGroup("etl_group");
        factory.setName("etl_job");
        factory.setDurability(true);
        return factory;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(1000);
        stFactory.setName("cron_trigger");
        stFactory.setGroup("cron_group");
        stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(cronTriggerFactoryBean().getObject());
        return scheduler;
    }

}