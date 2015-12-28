package com.neu.project.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {

	private String redirectUrl;
	 private String excepUrl;
	 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
			  HttpServletRequest request = (HttpServletRequest)req;
			  HttpServletResponse response = (HttpServletResponse)res;
			  String servletPath = request.getServletPath();
			  if (servletPath.equals("/"+redirectUrl) || excepUrl.contains(servletPath)) {
				   chain.doFilter(req, res);
				   return;
				  }
			  Object sessionObj1 = request.getSession().getAttribute("patient");
			  Object sessionObj2 = request.getSession().getAttribute("doctor");
			  Object sessionObj3 = request.getSession().getAttribute("admin");
			  System.out.println("////////////////////////////// "+sessionObj1);
			  if (sessionObj1 == null&&sessionObj2 == null&&sessionObj3 == null) {
				  response.sendRedirect(redirectUrl);
				    } else {
				     chain.doFilter(req, res);
				   }
				   }
		
	

	@Override
	public void init(FilterConfig cfg) throws ServletException {

		redirectUrl = cfg.getInitParameter("redirectUrl");
		excepUrl = cfg.getInitParameter("excepUrl");
		System.out.println("you should not do this!");
		
	}
	
}
