package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.dao.DepartmentDao;
import com.icss.vo.Department;

public class DepartmentService {
	public ArrayList<Department> showAllDepartmentService() throws ClassNotFoundException, SQLException{
		DepartmentDao dd=new DepartmentDao();
		return dd.showAllDepartment();
		
	}

}
