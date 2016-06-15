package com.epam.pp.hasan.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Constants;
import com.epam.pp.hasan.entity.Role;
import com.epam.pp.hasan.entity.User;
import com.epam.pp.hasan.web.filter.access.chain.AccessChain;
import com.epam.pp.hasan.web.filter.access.chain.impl.AvailableAccessChain;
import com.epam.pp.hasan.web.filter.access.chain.impl.RoleAccessChain;
import com.epam.pp.hasan.web.filter.access.chain.impl.UrlAccessChain;
import com.epam.pp.hasan.web.filter.access.map.AccessMap;
import com.epam.pp.hasan.web.filter.access.map.AccessMapHolder;
import com.epam.pp.hasan.web.filter.access.parser.RequestParser;
import com.epam.pp.hasan.web.filter.access.parser.impl.HttpRequestParserImpl;
import com.epam.pp.hasan.web.filter.access.xml.Constraint;
import com.epam.pp.hasan.web.filter.access.xml.Security;

/**
 * Encoding filter.
 * 
 * @author Yosin_Hasan
 * 
 */
public class AccessFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(AccessFilter.class);
	private Security security;
	private RequestParser parser;
	private AccessMapHolder accessMap;
	private ArrayList<String> urls;
	private AccessChain filter;

	@Override
	public final void destroy() {
		LOG.debug("Access filter destruction starts");
		// no op
		LOG.debug("Access filter destruction finished");
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Access filter starts");
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		User user = (User) req.getSession().getAttribute("user");
		Role role = null;
		if (user != null) {
			role = user.getRole();
		}
		LOG.info("Role -> " + role);
		LOG.info("URI -> " + uri);
		parser.parse(req);
		filter.processRequest(req, res, chain, parser.getAction(), role);
	}

	@Override
	public final void init(final FilterConfig fConfig) throws ServletException {
		LOG.debug("Access filter initialization starts");
		security = (Security) fConfig.getServletContext().getAttribute(Constants.SECURITY);
		parser = new HttpRequestParserImpl();
		initAccessMap(security);
		initFilter();
		LOG.debug("Access filter initialization finished");
	}

	private void initFilter() {
		AvailableAccessChain available = new AvailableAccessChain(null, accessMap, urls);
		RoleAccessChain role = new RoleAccessChain(available, accessMap, urls);
		filter = new UrlAccessChain(role, accessMap, urls);
	}

	private void initAccessMap(Security security) {
		LOG.info("Init access map started");
		Map<Role, AccessMap> map = new HashMap<>();
		map.put(Role.ADMIN, new AccessMap(new ArrayList<>()));
		map.put(Role.USER, new AccessMap(new ArrayList<>()));
		accessMap = new AccessMapHolder(map);
		urls = new ArrayList<>();
		List<Constraint> constraints = security.getConstraint();
		for (Constraint constraint : constraints) {
			for (Role role : constraint.getRole()) {
				accessMap.get(role).add(constraint.getUrlPattern());
			}
			urls.add(constraint.getUrlPattern());
		}
		LOG.info("Access map for admin role -> " + accessMap.get(Role.ADMIN).getUrlList());
		LOG.info("Access map for user role -> " + accessMap.get(Role.USER).getUrlList());
		LOG.info("Init access map ended");
	}
}