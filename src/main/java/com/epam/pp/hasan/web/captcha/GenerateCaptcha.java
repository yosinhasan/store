package com.epam.pp.hasan.web.captcha;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yosin_Hasan
 */
public interface GenerateCaptcha {
    /**
     * Save captcha.
     *
     * @param captcha captcha
     */
    public abstract void save(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                              Integer captcha);

    /**
     * Validate captcha.
     *
     * @param request servlet request
     * @param context servlet context
     * @param value   value
     * @return boolean
     */
    public abstract boolean validate(HttpServletRequest request, ServletContext context, Integer value);

}
