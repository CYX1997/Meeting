package com.icss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.EmployeeService;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = request.getParameter("pwd");
		String repass = request.getParameter("repwd");
		String eid = request.getParameter("employeeid");
		int id = Integer.parseInt(eid);
		EmployeeService es = new EmployeeService();
		if (pass.equals(repass)) {
			es.updateEmployeeService(pass, id);
			request.setAttribute("msg", "修改密码成功，请重新登录！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("adminindex.jsp").forward(request, response);
		}

	}

}
