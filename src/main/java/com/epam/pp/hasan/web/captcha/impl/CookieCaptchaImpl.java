package com.epam.pp.hasan.web.captcha.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.pp.hasan.web.captcha.GenerateCaptcha;

/**
 * @author Yosin_Hasan
 */
public class CookieCaptchaImpl implements GenerateCaptcha {
	private String captchaName;
	private Long lifeTime;

	public CookieCaptchaImpl(String captchaName, Long lifeTime) {
		this.captchaName = captchaName;
		this.lifeTime = lifeTime;
	}

	public void save(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			Integer captcha) {
		String name = captchaName + request.getSession().getId();
		Cookie cookie = new Cookie("captcha", name);
		cookie.setMaxAge(lifeTime.intValue());
		response.addCookie(cookie);
		context.setAttribute(name, captcha);
	}

	public boolean validate(HttpServletRequest request, ServletContext context, Integer value) {
		Cookie[] cookies = request.getCookies();
		String name = null;
		if (cookies == null) {
			return false;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("captcha")) {
				name = cookie.getValue();
				break;
			}
		}
		if (name == null) {
			return false;
		}
		Integer number = (Integer) context.getAttribute(name);
		context.removeAttribute(name);
		if (number == null || value == null) {
			return false;
		}
		return value.equals(number);
	}
}
