package com.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.one.util.ConnectionDB;
import com.one.vo.User;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ConnectionDB connDB = new ConnectionDB();

	public User login(String uunm, String pwd) {
		User user = null;
		try {
			conn = connDB.ConnectDB();
			String sql = "select uno, uname from cm_user where uunm = ? and upwd = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uunm);
			psmt.setString(2, pwd);
			rs = psmt.executeQuery();
			if (rs.next())
				user = new User(rs.getInt(1), rs.getString(2));
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
		return user;
	}

	public int confirmPwd(int uno, String pwd) {
		int result = 0;
		try {
			conn = connDB.ConnectDB();
			String sql = "select uno from cm_user where uno = ? and upwd = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, uno);
			psmt.setString(2, pwd);
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

	public void changePwd(int uno, String upwd) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_user set upwd = ? where uno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, upwd);
			psmt.setInt(2, uno);
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

	public int confirmUnm(String uunm) {
		int result = 0;
		try {
			conn = connDB.ConnectDB();
			String sql = "select uno from cm_user where uunm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uunm);
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

	public void register(String uname, String uunm, String upwd, String uphone, String umail, String udept) {
		try {
			conn = connDB.ConnectDB();
			String sql = "insert into cm_user values(cm_user_no_seq.nextval, ?, ?, ?, ?, ?, 0, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uname);
			psmt.setString(2, uunm);
			psmt.setString(3, upwd);
			psmt.setString(4, uphone);
			psmt.setString(5, umail);
			psmt.setString(6, udept);
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

	public ArrayList<User> getApprove() {
		ArrayList<User> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select uname, uunm, uphone, umail from cm_user where ustate = 0";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
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

	public void pass(String uunm) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_user set ustate = 1 where uunm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uunm);
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

	public void notPass(String uunm) {
		try {
			conn = connDB.ConnectDB();
			String sql = "delete from cm_user where uunm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uunm);
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

	public ArrayList<User> select(String uname, String uunm, int ustate) {
		ArrayList<User> list = new ArrayList<>();
		String str = null;
		if (ustate == 0)
			str = "ustate = 0";
		if (ustate == 1)
			str = "ustate = 1";
		if (ustate == -1)
			str = "(ustate = 2 or ustate = 3)";
		try {
			conn = connDB.ConnectDB();
			if (uname != "" && (uunm != "" && uunm != null)) {
				String sql = "select uno, uname, uunm, uphone, umail from cm_user where uname = ? and uunm = ? and "
						+ str;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, uname);
				psmt.setString(2, uunm);
				rs = psmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), ustate);
					list.add(user);
				}
			}
			if (uname == "" && (uunm != "" && uunm != null)) {
				String sql = "select uno, uname, uunm, uphone, umail from cm_user where uunm = ? and " + str;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, uunm);
				rs = psmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), ustate);
					list.add(user);
				}
			}
			if (uname != "" && (uunm == "" || uunm == null)) {
				String sql = "select uno, uname, uunm, uphone, umail from cm_user where uname = ? and " + str;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, uname);
				rs = psmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), ustate);
					list.add(user);
				}
			}
			if (uname == "" && (uunm == "" || uunm == null)) {
				String sql = "select uno, uname, uunm, uphone, umail from cm_user where " + str;
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), ustate);
					list.add(user);
				}
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

	public void closeUser(String uno) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_user set ustate = ustate + 2 where uno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
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

	public void openUser(String uno) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_user set ustate = ustate - 2 where uno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
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
	public ArrayList<User> getUserDept(int dno){
		ArrayList<User> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select uno, uname from cm_user where udptno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2));
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
