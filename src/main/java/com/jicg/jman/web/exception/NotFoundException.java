package com.jicg.jman.web.exception;

import com.jicg.jman.bean.vo.R;
import com.jicg.jman.utils.Utils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jicg on 2020/8/7
 */
@Controller
public class NotFoundException implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletRequest req) {
        if (Utils.isJsonReq(req)) {
            return R.fail("404，资源不存在！");
        }
        return new ModelAndView("error/404");
    }
}
