package com.epam.pp.hasan.web.filter.access.chain.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.pp.hasan.config.Path;
import com.epam.pp.hasan.entity.Role;
import com.epam.pp.hasan.web.filter.access.chain.AccessChain;
import com.epam.pp.hasan.web.filter.access.map.AccessMapHolder;

public class AvailableAccessChain extends AccessChain {

	public AvailableAccessChain(AccessChain successor, AccessMapHolder holder, ArrayList<String> urls) {
		super(successor, holder, urls);
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse res, FilterChain chain, String action,
			Role role) throws IOException, ServletException {
		if (!this.holder.get(role).contains(action)) {
			String errorMessasge = "You do not have permission " + "to access the requested resource";
			req.setAttribute("errorMessage", errorMessasge);
			req.setAttribute("javax.servlet.error.status_code", 400);
			req.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(req, res);
		} else {
			chain.doFilter(req, res);
		}

	}

}
