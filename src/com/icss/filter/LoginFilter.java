package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		//将ServletRequest 转换成HttpServletRequest得到request对象，用来创建session对象
		HttpServletRequest request=(HttpServletRequest)arg0;
		//获取session对象
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		//判断登录 如果用户名为空则返回登录页面
		if(username==null){
			request.setAttribute("msg", "您还未登录，请先登录");
			request.getRequestDispatcher("login.jsp").forward(arg0, arg1);
		}
		arg2.doFilter(arg0, arg1);
	
		
}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		


}
}