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

public class RoleAccessChain extends AccessChain {

	public RoleAccessChain(AccessChain successor, AccessMapHolder holder, ArrayList<String> urls) {
		super(successor, holder, urls);
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse res, FilterChain chain, String action,
			Role role) throws IOException, ServletException {
		if (role != null) {
			if (successor != null) {
				successor.processRequest(req, res, chain, action, role);
			}
		} else {
			req.getSession().setAttribute("referer", req.getContextPath() + "/" + action);
			req.getRequestDispatcher(Path.SIGN_IN).forward(req, res);
		}

	}

}
