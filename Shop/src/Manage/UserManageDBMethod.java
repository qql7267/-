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

	// ��ʵ�û���������ķ���---0δ�ҵ�---1���벻��---2��ȷ
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

	// ��¼ע����Ϣ�ķ���
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

	// ��ѯ�û����Ƿ��Ѵ���---0������---1�Ѵ���
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

	// ��ȡ�û�����Ϣ
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
			result = "��ţ�" + rs.getInt(1) + "\t\t�û�����" + rs.getString(2) + "\t���룺" + rs.getString(3) + "\t\t���У�"
					+ rs.getDouble(4) + "\t�ۿۣ�" + rs.getInt(5) + "\t\tȨ�ޣ�";
			if (rs.getInt(6) == 1)
				result += "���";
			if (rs.getInt(6) == 2)
				result += "����";
			if (rs.getInt(6) == 3)
				result += "����Ա";
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

	// ɾ���û�����---1ɾ���ɹ�---2�����˺�---3����Ա�˺�
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
			result = "��ţ�" + rs.getInt(1) + "\t\t���ƣ�" + rs.getString(2) + "\t\t��棺" + rs.getInt(3) + "\t\t���ۣ�"
					+ rs.getDouble(4) + "\t���" + rs.getString(5) + "\t\t���ң�" + rs.getInt(6);
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
			result = "��ţ�" + rs.getInt(1) + "\t\t�û�����" + rs.getString(2) + "\t�ۿۣ�" + rs.getInt(5) + "\t\tȨ�ޣ�";
			if (rs.getInt(6) == 1)
				result += "���";
			if (rs.getInt(6) == 2)
				result += "����";
			if (rs.getInt(6) == 3)
				result += "����Ա";
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
