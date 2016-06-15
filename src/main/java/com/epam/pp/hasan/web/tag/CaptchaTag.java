package com.epam.pp.hasan.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag for gaining amount of finance.
 * 
 * @author Yosin_Hasan
 * 
 */
public class CaptchaTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public final int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			String param = pageContext.getServletContext().getInitParameter("captchaMethod");
			String src = pageContext.getServletContext().getContextPath() + "/captcha";
			String img = "<img src='" + src + "' />";
			out.write(img);
			if (param.equals("form")) {
				String input = "<input name='captchaId' type='hidden' value='" + pageContext.getSession().getId()
						+ "'/>";
				out.write(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;

	}
}
