package com.icss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.EmployeeDao;
import com.icss.vo.Employee;


/**
 * Servlet implementation class ValidateUsernameServlet
 */
@WebServlet("/ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUsernameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean flag=true;
		String message="";
		EmployeeDao dao=new EmployeeDao();
		Employee username = null;
		try {
			username = dao.selectByName(request.getParameter("username"));
		}
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(username.getUsername()==null){
			message="用户名可以使用";
		}else{
			flag=false;
			message="用户名已经存在，请选择使用其他用户名";
		}
        //将校验的结果，以XML格式返回给客户端
		 response.setContentType("text/xml;charset=utf-8");
		 PrintWriter out = response.getWriter();  
		 response.setHeader("Cache-Control","no-cache");
	     out.println("<?xml version='1.0' encoding='"+"utf-8"+"' ?>");
	     out.println("<response>");
	     out.println("<passed>" + Boolean.toString(flag) + "</passed>");
	     out.println("<message>" + message + "</message>");
	     out.println("</response>");
	     out.close();	
	     }	
}