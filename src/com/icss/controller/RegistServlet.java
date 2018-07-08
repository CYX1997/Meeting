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
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("employeename");
		byte [] a=name.getBytes("ISO-8859-1");
		String empname=new String(a,"UTF-8");
		String username1=request.getParameter("username");
		byte [] b=username1.getBytes("ISO-8859-1");
		String username=new String(b,"UTF-8");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String deptid=request.getParameter("deptid");
		int depaid=Integer.parseInt(deptid);
		EmployeeService es=new EmployeeService();
		if(password.equals(password1)){
				try {
					es.insertService(empname, username, password, phone, email, depaid);
					request.setAttribute("msg", "注册成功,您可以登录啦！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
			

		}
		
	}

}
