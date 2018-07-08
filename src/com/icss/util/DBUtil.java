package com.icss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn=null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","scott","tiger");
		return conn;
	}
	public static void close() throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}


}
