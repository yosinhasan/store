package com.epam.pp.hasan.web.filter.access.chain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.pp.hasan.entity.Role;
import com.epam.pp.hasan.web.filter.access.map.AccessMapHolder;

public abstract class AccessChain {
	protected AccessChain successor;
	protected AccessMapHolder holder;
	protected ArrayList<String> urls;

	public AccessChain(AccessChain successor, AccessMapHolder holder, ArrayList<String> urls) {
		this.successor = successor;
		this.holder = holder;
		this.urls = urls;
	}

	abstract public void processRequest(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			String action, Role role) throws IOException, ServletException;

}
