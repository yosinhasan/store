package com.epam.pp.hasan.web.servlet;

import com.epam.pp.hasan.util.CaptchaUtil;
import com.epam.pp.hasan.web.captcha.GenerateCaptcha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenerateCaptcha captchaInstance;

	@Override
	public void init() throws ServletException {
		captchaInstance = (GenerateCaptcha) getServletContext().getAttribute("captchaInstance");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");
		int randomKey = (int) (Math.random() * 123456789);
		captchaInstance.save(request, response, getServletContext(), randomKey);
		response.getOutputStream().write(CaptchaUtil.generateCaptcha(randomKey));
	}
}
