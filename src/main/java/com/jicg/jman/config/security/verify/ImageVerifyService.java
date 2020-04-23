package com.jicg.jman.config.security.verify;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.jicg.jman.config.security.verify.IVerifyCodeService;
import com.jicg.jman.config.security.verify.VerifyCodeBean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jicg on 2020/4/23
 */
@Component
public class ImageVerifyService implements IVerifyCodeService {
    @Override
    public VerifyCodeBean create(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(
                200, 100, 4, 20
        );
        RandomGenerator randomGenerator = new RandomGenerator(
                "0123456789qwertyuiopasdfghjklzxcvbnm", 4
        );
        captcha.setGenerator(randomGenerator);
        String code = captcha.getCode();
        captcha.write(response.getOutputStream());
        return new VerifyCodeBean(code, 60);
    }



}
