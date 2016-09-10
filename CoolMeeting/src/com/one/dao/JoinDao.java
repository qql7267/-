package com.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.one.util.ConnectionDB;
import com.one.vo.User;

public class JoinDao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ConnectionDB connDB = new ConnectionDB();

	public void addJoin(String jparter, String jmno) {
		try {
			conn = connDB.ConnectDB();
			String sql = "insert into cm_join values(cm_join_no_seq.nextval, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, jparter);
			psmt.setString(2, jmno);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<User> getJoin(String jmno) {
		ArrayList<User> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select u.uname, u.uphone, u.umail from cm_user u, cm_join j where u.uno = j.jparter and j.jmno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, jmno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
