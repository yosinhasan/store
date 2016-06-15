package com.epam.pp.hasan.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/logout.html" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LogoutServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		destroySession(request, response);
		response.sendRedirect(getServletContext().getContextPath() + "/index.html");
	}

	private void destroySession(HttpServletRequest request, HttpServletResponse response) {
		LOG.info("Destroy session");
		HttpSession session = request.getSession();
		session.invalidate();
	}

}
