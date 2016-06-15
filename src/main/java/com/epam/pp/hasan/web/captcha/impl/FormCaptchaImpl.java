package com.epam.pp.hasan.web.captcha.impl;

import com.epam.pp.hasan.web.captcha.GenerateCaptcha;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * @author Yosin_Hasan
 */
public class FormCaptchaImpl implements GenerateCaptcha {
	private String captchaName;
	private Long lifeTime;

	public FormCaptchaImpl(String captchaName, Long lifeTime) {
		this.captchaName = captchaName;
		this.lifeTime = lifeTime;
	}

	public void save(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			Integer captcha) {
		String name = captchaName + request.getSession().getId();
		String endTime = captchaName + request.getSession().getId() + "time";
		Long time = Calendar.getInstance().getTimeInMillis() + (lifeTime * 1000);
		context.setAttribute(name, captcha);
		context.setAttribute(endTime, time);
	}

	public boolean validate(HttpServletRequest request, ServletContext context, Integer value) {
		String captchaId = request.getParameter("captchaId");
		if (captchaId == null) {
			return false;
		}
		String name = captchaName + captchaId;
		String captchaEndTime = name + "time";

		Integer number = (Integer) context.getAttribute(name);
		Long endTime = (Long) context.getAttribute(captchaEndTime);
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		context.removeAttribute(name);
		context.removeAttribute(captchaEndTime);
		if ((currentTime - endTime > 0) || number == null || value == null) {
			return false;
		}
		return value.equals(number);
	}

}
