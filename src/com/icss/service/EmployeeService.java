package com.icss.service;

import java.sql.SQLException;

import com.icss.dao.EmployeeDao;
import com.icss.vo.Employee;

public class EmployeeService {
	//保存登录成功后的employee对象
	private Employee loginedEmployee=new Employee();
	
	public int loginService(String username,String pass ){
		Employee emp=new Employee();
		EmployeeDao ed=new EmployeeDao();
		
		try {
			emp=ed.loginDao(username, pass);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		int flag=3;
		if(emp!=null){
			loginedEmployee=emp;
			if("0".equals(emp.getStatus())){
				flag=0;
			}else if("1".equals(emp.getStatus())){
				flag=1;
			}else if("2".equals(emp.getStatus())){
				flag=2;
			}
		}
		return flag;
	}
	public Employee getLoginedEmployee(){//登录成功的对象
		return loginedEmployee;
	}
	//修改前的查询
	public Employee updateSelectService(int eid) throws ClassNotFoundException, SQLException{
		EmployeeDao ed=new EmployeeDao();
		return ed.updateSelectDao(eid);
	}
	public void updateEmployeeService(String pass,int id) {
		EmployeeDao ed=new EmployeeDao();
		 try {
			ed.updateEmployeeDao(pass, id);
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//添加
	public void insertService(String employeename,String username,String password,
			String phone,String email,int departmentid) throws ClassNotFoundException, SQLException{
			EmployeeDao ed=new EmployeeDao();
			
			ed.insertDao(employeename, username, password, phone, email, departmentid);
	}
}
