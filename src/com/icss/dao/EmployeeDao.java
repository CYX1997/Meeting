package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DBUtil;
import com.icss.vo.Employee;

public class EmployeeDao {
	//登录
	public Employee loginDao(String username,String pass) throws ClassNotFoundException, SQLException{
		PreparedStatement ps=DBUtil.getConnection().prepareStatement
		("select * from employee where username=? and password=?");
		ps.setString(1, username);
		ps.setString(2, pass);
		ResultSet rs=ps.executeQuery();
		Employee emp=new Employee();
		while(rs.next()){
			int id=rs.getInt(1);
			String empname=rs.getString(2);
			String name=rs.getString(3);
			String pwd=rs.getString(8);
			String status=rs.getString(6);
			emp.setEmployeeid(id);
			emp.setUsername(name);
			emp.setPassword(pwd);	
			emp.setStatus(status);
			emp.setEmployeename(empname);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return emp;
		
	}
	//修改前的查询
	public Employee updateSelectDao(int eid) throws ClassNotFoundException, SQLException{
		PreparedStatement ps=DBUtil.getConnection().prepareStatement
		("select * from employee where employeeid=?");
		ps.setInt(1, eid);
		ResultSet rs=ps.executeQuery();
		Employee emp=new Employee();
		if(rs.next()){
			String name=rs.getString(2);
			String pass=rs.getString(8);
			emp.setPassword(pass);
			emp.setUsername(name);
		}
		return emp;
	}
	//修改
	public void updateEmployeeDao(String pass,int id) throws ClassNotFoundException, SQLException{
		PreparedStatement ps=DBUtil.getConnection().prepareStatement
				("update  employee set password=? where employeeid=?");
		ps.setString(1, pass);
		ps.setInt(2,id);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}
	//增加
	public void  insertDao(String employeename,String username,String password,
			String phone,String email,int departmentid) throws ClassNotFoundException, SQLException{
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(
				"insert into employee  values (emp_seq.nextval,?,?,?,?,'1',?,?,'2')");
		ps.setString(1,employeename );
		ps.setString(2,username );
		ps.setString(3,phone );
		ps.setString(4,email );
		ps.setInt(5,departmentid );
		ps.setString(6,password );
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	
	}
	//通过用户名查询
			public Employee selectByName(String username) throws ClassNotFoundException, SQLException {
				Employee emp=new Employee();
				PreparedStatement ps=DBUtil.getConnection().prepareStatement("select * from employee where username=?");
		   	    ps.setString(1, username);
		   	    ResultSet rs=ps.executeQuery();
		   	 while(rs.next()){
					int employeeid=rs.getInt(1);
					String employeename=rs.getString(2);
					String uname=rs.getString(3);
					String phone=rs.getString(4);
					String email=rs.getString(5);
					String status=rs.getString(6);		
					int departmentid=rs.getInt(7);
					String password=rs.getString(8);
					String role=rs.getString(9);
					emp.setEmployeeid(employeeid);
					emp.setEmployeename(employeename);
					emp.setUsername(uname);
					emp.setPhone(phone);
					emp.setEmail(email);
					emp.setStatus(status);
					emp.setDepartmentid(departmentid);
					emp.setPassword(password);
					emp.setRole(role);
				}
				rs.close();
				ps.close();
				DBUtil.close();
				return emp;
			}
}
