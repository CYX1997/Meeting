package com.icss.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.icss.dao.CounterDao;

public class VisitCountsListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {//销毁时
		// 获得上下文对象
		ServletContext sc = arg0.getServletContext();
		int visitcount=Integer.parseInt(sc.getAttribute("counts").toString());
		CounterDao cd=new CounterDao();
		try {
			cd.update(visitcount);
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {//初始化时
		CounterDao cd=new CounterDao();
		int visitcount=cd.select();
		ServletContext sc = arg0.getServletContext();
		sc.setAttribute("counts", visitcount);
		

	}

}
