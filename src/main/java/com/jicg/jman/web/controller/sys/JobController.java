package com.jicg.jman.web.controller.sys;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.JobForm;
import com.jicg.jman.bean.vo.Resp;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.orm.entity.JobAndTrigger;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {
    private final ISysJobService sysJobService;

    public JobController(ISysJobService sysJobService) {
        this.sysJobService = sysJobService;
    }

    @GetMapping(value = {"/index.html"}, produces = "text/html")
    public ModelAndView menuIndex() {
        return new ModelAndView("page/sys/job/index.html");
    }


    @GetMapping(path = {"/add.html", "/edit.html"}, produces = {"text/html"})
    public ModelAndView edit(JobForm form) {
        ModelAndView view = new ModelAndView("page/sys/job/edit.html");
        view.addObject("isEdit", true);
        if (StrUtil.hasBlank(form.getJobGroup(), form.getJobName())) {
            view.addObject("isEdit", false);
        }
        view.addObject("job", form);
        return view;
    }

    /**
     * 保存定时任务
     */
    @PostMapping(path = "add")
    public Resp<String> addJob(@Valid JobForm form) throws Exception {
        sysJobService.addJob(form);
        return Resp.ok("操纵成功");
    }

    /**
     * 删除定时任务
     */
    @PostMapping(path = "del")
    public Resp<String> deleteJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroup(), form.getJobName())) {
            throw new RuntimeException("参数不能为空");
        }
        sysJobService.deleteJob(form);
        return Resp.ok("删除成功");
    }

    /**
     * 暂停定时任务
     */
    @PostMapping(path = "pause")
    public Resp<String> pauseJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroup(), form.getJobName())) {
            throw new RuntimeException("参数不能为空");
        }
        sysJobService.pauseJob(form);
        return Resp.ok("暂停成功");
    }

    /**
     * 恢复定时任务
     */
    @PostMapping(path = "resume")
    public Resp<String> resumeJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroup(), form.getJobName())) {
            throw new RuntimeException("参数不能为空");
        }
        sysJobService.resumeJob(form);
        return Resp.ok("恢复成功");
    }

    /**
     * 修改定时任务，定时时间
     */
    @PostMapping(path = "cron")
    public Resp<String> cronJob(@Valid JobForm form) throws Exception {
        sysJobService.cronJob(form);
        return Resp.ok("修改成功");
    }

    @GetMapping("/list")
    public RespList<JobAndTrigger> jobList(long page, long limit) throws SchedulerException {
        if (ObjectUtil.isNull(page)) {
            page = 1;
        }
        if (ObjectUtil.isNull(limit)) {
            limit = 10;
        }
        return RespList.ok(sysJobService.selectPageVo(new Page<>(page, limit)));
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<String>> jobList2() throws SchedulerException {
//        return ResponseEntity.ok(sysJobService.findJobs());
//    }

}
