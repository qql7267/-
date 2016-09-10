package com.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.one.util.ConnectionDB;
import com.one.vo.Dept;

public class DeptDao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ConnectionDB connDB = new ConnectionDB();

	public int confirmDept(String dname) {
		int result = 0;
		try {
			conn = connDB.ConnectDB();
			String sql = "select dno from cm_dept where dname = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dname);
			rs = psmt.executeQuery();
			if (rs.next())
				result = 1;
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
		return result;
	}

	public void addDept(String dname) {
		try {
			conn = connDB.ConnectDB();
			String sql = "insert into cm_dept values(cm_dept_no_seq.nextval, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dname);
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

	public ArrayList<Dept> getAllDept() {
		ArrayList<Dept> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select * from cm_dept order by dno";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Dept dept = new Dept(rs.getInt(1), rs.getString(2));
				list.add(dept);
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

	public void delDept(int dno) {
		try {
			conn = connDB.ConnectDB();
			String sql = "delete from cm_dept where dno = " + dno;
			psmt = conn.prepareStatement(sql);
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
	public void updDept(String newDname,String dname){
		try {
			conn = connDB.ConnectDB();
			String sql = "Update cm_dept set dname = ? where dname = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, newDname);
			psmt.setString(2, dname);
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
}
