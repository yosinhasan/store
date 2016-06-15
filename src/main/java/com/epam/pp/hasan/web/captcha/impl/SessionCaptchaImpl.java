package com.epam.pp.hasan.web.captcha.impl;

import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.pp.hasan.web.captcha.GenerateCaptcha;

/**
 * @author Yosin_Hasan
 */
public class SessionCaptchaImpl implements GenerateCaptcha {
    private String captchaName;
    private Long lifeTime;

    public SessionCaptchaImpl(String captchaName, Long lifeTime) {
        this.captchaName = captchaName;
        this.lifeTime = lifeTime;
    }

    public void save(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                     Integer captcha) {
        request.getSession().setAttribute("captcha", captcha);
        Long time = Calendar.getInstance().getTimeInMillis() + (lifeTime * 1000);
        request.getSession().setAttribute("captchaEndTime", time);
    }

    public boolean validate(HttpServletRequest request, ServletContext context, Integer value) {
        HttpSession session = request.getSession();
        Integer number = (Integer) session.getAttribute("captcha");
        Long endTime = (Long) session.getAttribute("captchaEndTime");
        Long currentTime = Calendar.getInstance().getTimeInMillis();
        session.removeAttribute("captcha");
        session.removeAttribute("captchaEndTime");
        if ((currentTime - endTime) > 0) {
            return false;
        }
        if (number == null || value == null) {
            return false;
        }
        return value.equals(number);
    }

}
