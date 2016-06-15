package com.epam.pp.hasan.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.service.UserService;
import com.epam.pp.hasan.service.impl.MySqlUserServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/signin.html" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SigninServlet.class);
	private UserService userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(Path.SIGN_IN).forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		LOG.info("Init user service");
		DAOFactory factory = new MySqlDAOFactory();
		userService = new MySqlUserServiceImpl(factory.getUserDAO());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean failed = process(request, response);
		output(response, request, failed);
	}

	private boolean process(HttpServletRequest request, HttpServletResponse response) {
		LOG.info("Process given data");
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean failed = true;
		if (ValidateUtil.isValidEmail(email)) {
			User user = userService.findUser(email);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					LOG.info("User authorized. Save session.");
					session.setAttribute("user", user);
					failed = false;
				}
			}
		}
		return failed;
	}

	private void output(HttpServletResponse response, HttpServletRequest request, boolean failed) throws IOException {
		if (failed) {
			LOG.info("Return user to sign in page. Errors were found during authorization.");
			response.sendRedirect(getServletContext().getContextPath() + "/signin.html");
		} else {
			LOG.info("Send user to home page.");
			String referer = (String) request.getSession().getAttribute("referer");
			LOG.info("Referer: " + referer);
			if (referer == null) {
				referer = request.getServletContext().getContextPath() + "/index.html";
			}
			request.getSession().removeAttribute("referer");
			response.sendRedirect(referer);
		}
	}

}
