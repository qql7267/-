package Manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Service.ConnectionDB;

public class GoodsManageDBMethod {
	private ConnectionDB connDB = new ConnectionDB();
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public ArrayList<String> selectGoodsMethod_Seller(String selName) {
		ArrayList<String> goodList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select g.*,u.unm from goods_shop g,user_shop u where g.ownerid = u.id and u.unm = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, selName);
			rs = psmt.executeQuery();
			while (rs.next()) {
				goodList.add("商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3)
						+ " \t单价：  " + rs.getDouble(4) + " \t类别：" + rs.getString(5) + "\t\t店家：" + rs.getString(7));
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;
	}

	public ArrayList<String> selectGoodsMethod_Category(String category) {
		ArrayList<String> goodList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select g.*,u.unm from goods_shop g,user_shop u where g.ownerid = u.id and g.category = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, category);
			rs = psmt.executeQuery();
			while (rs.next()) {
				goodList.add("商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3)
						+ " \t单价：  " + rs.getDouble(4) + " \t类别：" + rs.getString(5) + "\t\t店家：" + rs.getString(7));
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;
	}

	public ArrayList<String> selectGoodsMethod_Name(String name) {
		ArrayList<String> goodList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select g.*,u.unm from goods_shop g,user_shop u where g.ownerid = u.id and g.name like '%"
					+ name + "%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				goodList.add("商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3)
						+ " \t单价：  " + rs.getDouble(4) + " \t类别：" + rs.getString(5) + "\t\t店家：" + rs.getString(7));
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;
	}

	public ArrayList<String> getList_Seller() {
		ArrayList<String> sellerList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select unm from user_shop where admlv = 2";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next())
				sellerList.add(rs.getString(1));
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sellerList;
	}

	public ArrayList<String> getList_Category() {
		ArrayList<String> categoryList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select distinct category from goods_shop";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next())
				categoryList.add(rs.getString(1));
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	public String getGoodInfoMethod(int id) {
		String result = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from goods_shop where id = " + id;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			result = "商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3) + " \t单价：  "
					+ rs.getDouble(4);
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

	public String getGoodInfoMethod(int id, int ownerId) {
		String result = null;
		try {
			conn = connDB.connDB();
			String sql = "select * from goods_shop where id = " + id + " and ownerId = " + ownerId;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				result = "商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3) + " \t单价：  "
						+ rs.getDouble(4);
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

	public void addGoodsMethod(String name, int stock, double price, String category, int ownerId) {
		int lastId;
		try {
			conn = connDB.connDB();
			String sqlId = "select id from goods_shop order by id desc";
			psmt = conn.prepareStatement(sqlId);
			rs = psmt.executeQuery();
			rs.next();
			lastId = rs.getInt(1);
			conn = connDB.connDB();
			String sql = "insert into goods_shop values(?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lastId + 1);
			psmt.setString(2, name);
			psmt.setInt(3, stock);
			psmt.setDouble(4, price);
			psmt.setString(5, category);
			psmt.setInt(6, ownerId);
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

	public ArrayList<String> selGoodMethod(int ownerId) {
		ArrayList<String> goodList = new ArrayList<String>();
		try {
			conn = connDB.connDB();
			String sql = "select * from goods_shop where ownerId = " + ownerId;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				goodList.add("商品编号： " + rs.getInt(1) + "\t商品名称： " + rs.getString(2) + "\t库存： " + rs.getInt(3)
						+ " \t单价：  " + rs.getDouble(4));
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;
	}

	public void chgGoodsMethod(int goodId, String name, int stock, double price, String category) {
		try {
			conn = connDB.connDB();
			String sql = "update goods_shop set name = ?, stock = ?, price = ?,category = ? where id =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setInt(2, stock);
			psmt.setDouble(3, price);
			psmt.setString(4, category);
			psmt.setInt(5, goodId);
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

	public void delGoodsMethod(int goodId) {
		try {
			conn = connDB.connDB();
			String sqlGoods = "delete from goods_shop where id = " + goodId;
			psmt = conn.prepareStatement(sqlGoods);
			psmt.executeUpdate();
			String sqlShopCart = "delete from shopcart_shop where goodid = " + goodId;
			psmt = conn.prepareStatement(sqlShopCart);
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
