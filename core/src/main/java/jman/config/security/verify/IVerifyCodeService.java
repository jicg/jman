package com.jicg.jman.config.security.verify;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jicg on 2020/4/23
 */

public interface IVerifyCodeService {
    default void callback(HttpSession session, HttpServletRequest request,
                          HttpServletResponse response) {

    }

    VerifyCodeBean create(HttpServletRequest request, HttpServletResponse response) throws IOException;

    default void verifyResp(HttpServletRequest request,
                            HttpServletResponse response) {
        VerifyCodeBean verifyCodeBean = null;
        try {
            verifyCodeBean = this.create(request, response);
            request.getSession()
                    .setAttribute(VerifyCodeBean.VERIFY_CODE_SESSION_KEY, verifyCodeBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    default void check(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletRequestBindingException, ValidateCodeException {
        String vaild_code = ServletRequestUtils.getStringParameter(request, getParameterName());
        HttpSession session = request.getSession();
        VerifyCodeBean verifyCodeBean =
                (VerifyCodeBean) session
                        .getAttribute(VerifyCodeBean.VERIFY_CODE_SESSION_KEY);

        if (verifyCodeBean == null) {
//            throw new ValidateCodeException("验证码不存在");
            return;
        }

        if (StrUtil.isEmpty(vaild_code)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (verifyCodeBean.isExpried()) {
//            session.removeAttribute(VerifyCodeBean.VERIFY_CODE_SESSION_KEY);
            throw new ValidateCodeException("验证码失效！！");
        }
        if (!StrUtil.equals(verifyCodeBean.getCode(), vaild_code)) {
            throw new ValidateCodeException("验证码不匹配");
        }
    }

    default String getParameterName() {
        return "verify_code";
    }
}
