package com.jicg.jman.orm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.orm.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author jicg on 2020/9/12
 */
@Component
public interface JobMapper {
    IPage<JobAndTrigger> list(Page<JobAndTrigger> page, @Param("sched_name") String sched_name);

    IPage<JobAndTrigger> selectPageVo(Page<?> page, @Param("sched_name") String sched_name);

    JobAndTrigger findOne(@Param("jog_name") String jobGroupName, @Param("sched_name") String jobClassName);
}
