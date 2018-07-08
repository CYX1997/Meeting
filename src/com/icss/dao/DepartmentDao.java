package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.icss.util.DBUtil;
import com.icss.vo.Department;

public class DepartmentDao {
	//查询所有的部门
	public ArrayList<Department> showAllDepartment() throws SQLException, ClassNotFoundException{
		ArrayList<Department> departments=new ArrayList<Department>();
		PreparedStatement ps=DBUtil.getConnection().prepareStatement("select * from department" );
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Department dep=new Department();
			int id=rs.getInt(1);
			dep.setDepartmentid(id);
			String name=rs.getString(2);
			dep.setDepartmentname(name);
			departments.add(dep);		
		}
	
		rs.close();
		ps.close();
		DBUtil.close();
		return departments;
	}
	


}
