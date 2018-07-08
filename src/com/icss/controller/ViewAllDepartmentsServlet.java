package com.icss.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.DepartmentService;
import com.icss.vo.Department;

/**
 * Servlet implementation class ViewAllDepartmentsServlet
 */
@WebServlet("/ViewAllDepartmentsServlet")
public class ViewAllDepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllDepartmentsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		DepartmentService ds=new DepartmentService();
		ArrayList<Department> list=new ArrayList<Department>();
		
				try {
					list=ds.showAllDepartmentService();
				}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		request.setAttribute("departmentsList", list);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
