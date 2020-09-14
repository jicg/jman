package com.jicg.jman.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.JobForm;
import com.jicg.jman.orm.entity.JobAndTrigger;
import com.jicg.jman.orm.mapper.JobMapper;
import com.jicg.jman.service.ISysJobService;
import com.jicg.jman.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @package: com.xkcoding.task.quartz.service.impl
 * @description: Job Service
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:25
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class SysJobService implements ISysJobService {
    private final Scheduler scheduler;
    private final JobMapper jobMapper;

    @Autowired
    public SysJobService(Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @return {@link JobDetail}
     * @throws Exception 异常
     */
    @Override
    public void addJob(JobForm form) throws Exception {
        // 启动调度器
        scheduler.start();

        // 构建Job信息
        JobDetail jobDetail =
                JobBuilder.newJob(Utils.getClass(form.getJobClassName()).getClass())
                        .withIdentity(form.getJobName(), form.getJobGroup()).withDescription("JMan").build();
        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobName(), form.getJobGroup()).withSchedule(cron).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】创建失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }

    }

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void deleteJob(JobForm form) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobName(), form.getJobGroup()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobName(), form.getJobGroup()));
        scheduler.deleteJob(JobKey.jobKey(form.getJobName(), form.getJobGroup()));
    }

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void pauseJob(JobForm form) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(form.getJobName(), form.getJobGroup()));
    }


    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobName(), form.getJobGroup()));
    }

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    @Override
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobName(), form.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 根据Cron表达式构建一个Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】更新失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    @Override
    public IPage<JobAndTrigger> list(Integer currentPage, Integer pageSize) throws SchedulerException {
        return jobMapper.list(new Page<JobAndTrigger>(currentPage, pageSize), scheduler.getSchedulerName());
    }

//    @Override
//    public List<String> findJobs() throws SchedulerException {
//        return scheduler.getJobGroupNames();
//    }

    @Override
    public IPage<JobAndTrigger> selectPageVo(Page<?> page) throws SchedulerException {
        return jobMapper.selectPageVo(page,scheduler.getSchedulerName());
    }

//    @Override
//    public JobAndTrigger getJob(String jobGroupName, String getJobName) {
//        return jobMapper.findOne(jobGroupName,getJobName);
//    }
}
