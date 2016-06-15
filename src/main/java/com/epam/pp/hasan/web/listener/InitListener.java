package com.epam.pp.hasan.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Constants;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.web.captcha.CaptchaProvider;
import com.epam.pp.hasan.web.exception.AppException;
import com.epam.pp.hasan.web.filter.access.xml.Security;
import com.epam.pp.hasan.web.filter.access.xml.parser.DOMParser;

/**
 * @author Yosin_Hasan
 */
public class InitListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(InitListener.class);

	public final void contextInitialized(final ServletContextEvent sce) {
		LOG.info("Servlet context initialization starts");
		String method = sce.getServletContext().getInitParameter("captchaMethod");
		Long captchaLifeTime = Long.parseLong(sce.getServletContext().getInitParameter("captchaLifeTime"));
		CaptchaProvider provider = new CaptchaProvider(method, Constants.CAPTCHA_NAME, captchaLifeTime);
		sce.getServletContext().setAttribute("captchaInstance", provider.getCaptchaInstance());
		LOG.info("Servlet context initialization finished");

		String path = sce.getServletContext().getInitParameter("securityFilePath");
		String securityPath = sce.getServletContext().getRealPath(path);
		DOMParser parser = new DOMParser(securityPath);
		try {
			parser.parse(true);
			Security security = parser.getSecurity();
			LOG.info("Security configuration: " + security);
			sce.getServletContext().setAttribute(Constants.SECURITY, security);
		} catch (Exception ex) {
			throw new AppException(Messages.XML_PARSER_ERROR, ex);
		}
	}

	public void contextDestroyed(final ServletContextEvent sce) {
	}

}
