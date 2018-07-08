package com.icss.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

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
		EmployeeService es=new EmployeeService();
		String username=request.getParameter("username");
		String pass=request.getParameter("pwd");
		request.setAttribute("username", username);
		int num=es.loginService(username, pass);
		if(num==0){
			request.setAttribute("msg","审核中，登录失败");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(num==1){
			//******************session *******************
			//在登录成功的情况下 使用session设置右上角的欢迎提示
			HttpSession session=request.getSession();//创建session对象
			String employeename=es.getLoginedEmployee().getEmployeename();
			session.setAttribute("employeename",employeename);
			int employeeid=es.getLoginedEmployee().getEmployeeid();
			session.setAttribute("employeeid", employeeid);
			session.setAttribute("username", username);
			//******************session *******************
			
			
			
			//******************上下文 *******************
			ServletContext sc=this.getServletContext();//创建上下文对象
			Object countStr=sc.getAttribute("counts");//获得页面里的counts的值
			int count=0;
			if(countStr==null){//如果访问值是空则给count 赋值1
				count=1;
			}else{
				count=Integer.parseInt(countStr.toString());
				count++;
			}                                                                                
			sc.setAttribute("counts", count);
			//******************上下文 *******************
			//******************cookie *******************
			String time=request.getParameter("timelength");
			int day=0;
			if(time!=null){
				day=Integer.parseInt(time);
			}
			//获得cookie对象
			if(day!=0){
				Cookie cooUserName=new Cookie("username",username);
				Cookie cooPass=new Cookie("pass",pass);
				//设置cookie的失效时间
				cooUserName.setMaxAge(day*60*2);
				cooPass.setMaxAge(day*60*4);
				response.addCookie(cooUserName);
				response.addCookie(cooPass);
				
			}
			
			//******************cookie *******************
			
			request.getRequestDispatcher("adminindex.jsp").forward(request, response);
		}else if(num==2){
			request.setAttribute("msg","审核失败，登录失败");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(num==3){
			request.setAttribute("msg","用户名或密码错误，登录失败");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
