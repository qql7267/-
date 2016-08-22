package Manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Goods;
import Entity.User;
import Service.ConnectionDB;

public class UserManageDBMethod {
	private ConnectionDB connDB = new ConnectionDB();
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// 核实用户名和密码的方法---0未找到---1密码不对---2正确
	public int verifyMethod(String unm, String pwd) {
		int result = 0;
		try {
			conn = connDB.connDB();
			String sql = "select unm, pwd from user_shop where unm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unm);
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(2).equals(pwd))
					result = 2;
				else
					result = 1;
			} else {
				result = 0;
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
		}
		return result;
	}

	// 记录注册信息的方法
	public void recordMethod(String unm, String pwd) {
		int lastId;
		try {
			conn = connDB.connDB();
			String sqlId = "select id from user_shop order by id desc";
			psmt = conn.prepareStatement(sqlId);
			rs = psmt.executeQuery();
			rs.next();
			lastId = rs.getInt(1);
			String sql = "insert into user_shop values(?,?,?,1000,10,1,1)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, (lastId + 1));
			psmt.setString(2, unm);
			psmt.setString(3, pwd);
			psmt.executeUpdate();
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
		}
	}

	// 查询用户名是否已存在---0不存在---1已存在
	public int selectUnm(String unm) {
		int result = 0;
		try {
			conn = connDB.connDB();
			String sql = "select unm from user_shop where unm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unm);
			rs = psmt.executeQuery();
			if (rs.next())
				result = 1;
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 获取用户的信息
	public User getInfoMethod(String unm) {
		User user = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from user_shop where unm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unm);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUnm(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setMoney(rs.getDouble(4));
				user.setLv(rs.getInt(5));
				user.setAdmin(rs.getInt(6));
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public String getInfoMethod(int id) {
		String result = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from user_shop where id = " + id;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			result = "编号：" + rs.getInt(1) + "\t\t用户名：" + rs.getString(2) + "\t密码：" + rs.getString(3) + "\t\t持有："
					+ rs.getDouble(4) + "\t折扣：" + rs.getInt(5) + "\t\t权限：";
			if (rs.getInt(6) == 1)
				result += "买家";
			if (rs.getInt(6) == 2)
				result += "卖家";
			if (rs.getInt(6) == 3)
				result += "管理员";
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<User> selUserMethod() {
		ArrayList<User> list = new ArrayList<>();
		User user = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from user_shop";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUnm(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setMoney(rs.getDouble(4));
				user.setLv(rs.getInt(5));
				user.setAdmin(rs.getInt(6));
				list.add(user);
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 删除用户方法---1删除成功---2卖家账号---3管理员账号
	public int delUserMethod(int userId) {
		int result = 1;
		try {
			conn = connDB.connDB();
			String sql = "select * from user_shop where id = " + userId;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getInt(6) == 3)
				result = 3;
			if (rs.getInt(6) == 2) {
				String sqlSell = "select * from goods_shop where ownerid = " + rs.getInt(1);
				psmt = conn.prepareStatement(sqlSell);
				rs = psmt.executeQuery();
				if (rs.next())
					result = 2;
			}
			if (result != 2 && result != 3)
				if (rs.getInt(6) == 1) {
					String sqlDel = "delete from user_shop where id = " + rs.getInt(1);
					psmt = conn.prepareStatement(sqlDel);
					psmt.executeUpdate();
				}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Goods> getGoodsMethod() {
		ArrayList<Goods> list = new ArrayList<>();
		Goods good = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from goods_shop";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				good = new Goods();
				good.setId(rs.getInt(1));
				good.setName(rs.getString(2));
				good.setStock(rs.getInt(3));
				good.setPrice(rs.getDouble(4));
				good.setCategory(rs.getString(5));
				good.setOwnerId(rs.getInt(6));
				list.add(good);
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void delGoodsMethod(int id) {
		try {
			conn = connDB.connDB();
			String sqlDel = "delete from goods_shop where id = " + id;
			psmt = conn.prepareStatement(sqlDel);
			psmt.executeUpdate();
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getGoodsInfoMethod(int id) {
		String result = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from goods_shop where id = " + id;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			result = "编号：" + rs.getInt(1) + "\t\t名称：" + rs.getString(2) + "\t\t库存：" + rs.getInt(3) + "\t\t单价："
					+ rs.getDouble(4) + "\t类别：" + rs.getString(5) + "\t\t卖家：" + rs.getInt(6);
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getLevelMethod(int id) {
		String result = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from user_shop where id = " + id;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			result = "编号：" + rs.getInt(1) + "\t\t用户名：" + rs.getString(2) + "\t折扣：" + rs.getInt(5) + "\t\t权限：";
			if (rs.getInt(6) == 1)
				result += "买家";
			if (rs.getInt(6) == 2)
				result += "卖家";
			if (rs.getInt(6) == 3)
				result += "管理员";
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void chgLevelMethod(int id, int level) {
		try {
			conn = connDB.connDB();
			String sql = "update user_shop set offlv = " + level + "where id = " + id;
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void chgAdminMethod(int id, int level) {
		try {
			conn = connDB.connDB();
			String sql = "update user_shop set admlv = " + level + "where id = " + id;
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
