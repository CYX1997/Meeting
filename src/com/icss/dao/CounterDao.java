package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DBUtil;

public class CounterDao {
	//更新
	public void update(int visitcount) throws ClassNotFoundException, SQLException{
		PreparedStatement ps=DBUtil.getConnection().prepareStatement
				("update counter set visitcount=?");
		ps.setInt(1,visitcount );
		ps.executeUpdate();
	}
	//查询
	public int select() {
		PreparedStatement ps = null;
		try {
			ps = DBUtil.getConnection().prepareStatement
			("select * from counter");
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		int visitcount=0;
		try {
			rs = ps.executeQuery();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				visitcount = rs.getInt("visitcount");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return visitcount;
	}
}


