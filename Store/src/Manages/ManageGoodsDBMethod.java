package Manages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Servers.StoreDB;
import Util.Goods;

public class ManageGoodsDBMethod {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	StoreDB sdb = new StoreDB();

	public void addDB(Goods good) {
		try {
			conn = sdb.connOracle();
			String sql = "insert into store values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, good.getName());
			psmt.setString(2, good.getNum());
			psmt.setString(3, good.getSum());
			psmt.setString(4, good.getPrice());
			psmt.executeUpdate();
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String updDB(Goods good) {
		String result = null;
		try {
			conn = sdb.connOracle();
			if (good.getNum() != null) {
				String sql = "update store set num = " + good.getNum() + " where name = " + good.getName();
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
			}
			if (good.getSum() != null) {
				String sql = "update store set sum = " + good.getSum() + " where name = " + good.getName();
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
			}
			if (good.getPrice() != null) {
				String sql = "update store set price = " + good.getPrice() + " where name = " + good.getName();
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
			}
			String sqlSel = "select * from store where name = " + good.getName();
			psmt = conn.prepareStatement(sqlSel);
			rs = psmt.executeQuery();
			rs.next();
			result = "已修改商品" + "\n商品名称：" + rs.getString(1) + "\n商品编号：" + rs.getString(2) + "\n商品数量：" + rs.getString(3)
					+ "\n商品单价：" + rs.getString(4);
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String delDB(String name) {
		String result = null;
		try {
			conn = sdb.connOracle();
			String sqlSel = "select * from store where name = " + name;
			psmt = conn.prepareStatement(sqlSel);
			rs = psmt.executeQuery();
			rs.next();
			result = "已删除商品" + "\n商品名称：" + rs.getString(1) + "\n商品编号：" + rs.getString(2) + "\n商品数量：" + rs.getString(3)
					+ "\n商品单价：" + rs.getString(4);
			String sql = "delete from store where name = " + name;
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String selDB(String name) {
		String result = null;
		try {
			conn = sdb.connOracle();
			String sqlSel = "select * from store where name = " + name;
			psmt = conn.prepareStatement(sqlSel);
			rs = psmt.executeQuery();
			rs.next();
			result = "已查询商品" + "\n商品名称：" + rs.getString(1) + "\n商品编号：" + rs.getString(2) + "\n商品数量：" + rs.getString(3)
					+ "\n商品单价：" + rs.getString(4);
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectName(String name) {
		int result = 0;
		try {
			conn = sdb.connOracle();
			String sql = "select * from store where name = " + name;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				result = 1;
			else
				result = 0;
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return 1;
		return 0;
	}

	public int selectNum(String num) {
		int result = 0;
		try {
			conn = sdb.connOracle();
			String sql = "select * from store where name = " + num;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				result = 1;
			else
				result = 0;
			sdb.closeOracle();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 1)
			return 1;
		return 0;
	}
}
