package com.epam.pp.hasan.web.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.db.dao.factory.DAOFactory;
import com.epam.pp.hasan.db.dao.factory.impl.MySqlDAOFactory;
import com.epam.pp.hasan.entity.Role;
import com.epam.pp.hasan.entity.Sex;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.service.UserService;
import com.epam.pp.hasan.service.impl.MySqlUserServiceImpl;
import com.epam.pp.hasan.util.ValidateUtil;
import com.epam.pp.hasan.web.captcha.GenerateCaptcha;

/**
 * 
 * @author Yosin_Hasan
 *
 */
@WebServlet(urlPatterns = { "/signup.html" })
@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> data;
	private String errorMsg;
	private GenerateCaptcha captchaInstance;
	private UserService userService;
	private static final Logger LOG = Logger.getLogger(UserServlet.class);

	@Override
	public void init() throws ServletException {
		LOG.info("Initialize user servlet.");
		data = new HashMap<String, String>();
		captchaInstance = (GenerateCaptcha) getServletContext().getAttribute("captchaInstance");
		DAOFactory factory = new MySqlDAOFactory();
		userService = new MySqlUserServiceImpl(factory.getUserDAO());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Servlet sign up, Method post");
		User user = new User();
		LOG.info("Set request data to user fields");
		initUser(user, request);
		LOG.info("Validate user fields");
		Boolean error = process(user, request);
		output(request, response, error);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(Path.SIGN_UP).forward(request, response);
	}

	private void initUser(User user, HttpServletRequest request) {
		LOG.info("Set request data to user fields");
		user.setName(request.getParameter("firstName"));
		user.setSurname(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setPassword(request.getParameter("password"));
		String param = request.getParameter("sex").trim();
		Integer index = Integer.parseInt(param);
		user.setSex(Sex.getSex(index));
		user.setRepeatPassword(request.getParameter("repeatPassword"));
		user.setRole(Role.USER);
	}

	private boolean process(User user, HttpServletRequest request) throws IOException, ServletException {
		LOG.info("Validate user ");
		boolean error = true;
		String captcha = request.getParameter("captcha").trim();
		LOG.info("Captcha: " + captcha);
		Integer value = Integer.parseInt(captcha);
		if (ValidateUtil.validateUser(user) && captchaInstance.validate(request, getServletContext(), value)) {
			User usr = userService.findUser(user.getEmail());
			if (usr == null) {
				LOG.info("No errors found. Set error to false");
				userService.addUser(user);
				importImage(request, user);
				error = false;
			} else {
				errorMsg = "Error: duplicate email. User with given email already exists.";
			}
		} else {
			data.put("firstName", user.getName());
			data.put("lastName", user.getSurname());
			data.put("phone", user.getPhone());
			data.put("email", user.getEmail());
			errorMsg = "Invalid data.";
		}
		return error;
	}

	private void output(HttpServletRequest request, HttpServletResponse response, Boolean error) throws IOException {
		if (error) {
			LOG.info("Return user to sign up page. Errors were found during validation.");
			request.getSession().setAttribute("data", data);
			request.getSession().setAttribute("errorMsg", errorMsg);
			response.sendRedirect(getServletContext().getContextPath() + "/signup.html");
		} else {
			LOG.info("Send user to home page.");
			data.clear();
			request.getSession().removeAttribute("data");
			request.getSession().removeAttribute("errorMsg");
			response.sendRedirect(getServletContext().getContextPath() + "/index.html");
		}
	}

	private void importImage(HttpServletRequest request, User user) throws IOException, ServletException {
		LOG.info("import image");
		final Part filePart = request.getPart("avatar");
		final String fileName = "avatar" + user.getId() + ".jpg";
		String path = getServletContext().getInitParameter("imagePath");
		String rootPath = request.getServletContext().getRealPath(path);
		File dir = new File(rootPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File avatar = new File(dir.getAbsolutePath() + File.separator + fileName);
		try (OutputStream out = new FileOutputStream(avatar); InputStream filecontent = filePart.getInputStream()) {
			int read;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			LOG.info("File: " + fileName + " was uploaded");
		} catch (FileNotFoundException ex) {
			LOG.error("Problems during file upload.", ex);
		}
	}

}
