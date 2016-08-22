package Manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.ShopCart;
import Entity.User;
import Service.ConnectionDB;

public class ShopCartManageDBMethod {
	private ConnectionDB connDB = new ConnectionDB();
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public ArrayList<ShopCart> getMyShopCartMethod(User user) {
		ArrayList<ShopCart> shopCartList = new ArrayList<>();
		try {
			conn = connDB.connDB();
			String sql = "select s.*,g.ownerid, g.name, g.price from shopcart_shop s, goods_shop g where s.goodid = g.id and s.buyerId = "
					+ user.getId();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ShopCart sc = new ShopCart(rs.getInt(1), rs.getInt(2), rs.getInt(5), rs.getInt(3), rs.getString(6),
						rs.getInt(4), rs.getDouble(7));
				shopCartList.add(sc);
			}
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			connDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shopCartList;
	}

	public void addShopCartMethod(int goodId, int buyerId, int num) {
		int lastId;
		try {
			conn = connDB.connDB();
			String sql = "select id from shopcart_shop where goodId = " + goodId + " and buyerId = " + buyerId;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				String sqlUpd = "update shopcart_shop set num = num + " + num + " where id = " + rs.getInt(1);
				psmt = conn.prepareStatement(sqlUpd);
				psmt.executeUpdate();
			} else {
				String sqlId = "select id from shopcart_shop order by id desc";
				psmt = conn.prepareStatement(sqlId);
				rs = psmt.executeQuery();
				rs.next();
				lastId = rs.getInt(1);
				String sqlAdd = "insert into shopcart_shop values (?,?,?,?)";
				psmt = conn.prepareStatement(sqlAdd);
				psmt.setInt(1, (lastId + 1));
				psmt.setInt(2, buyerId);
				psmt.setInt(3, goodId);
				psmt.setInt(4, num);
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
	}

	public void delShopCartMethod(int id) {
		try {
			conn = connDB.connDB();
			String sql = "delete from shopcart_shop where id = " + id;
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

	public int payAllShopCart(ArrayList<ShopCart> list) {
		int state_b = 0; // 是否存在数量不足--返回
		int state; // 数量是否足够--判定
		for (int i = 0; i < list.size(); i++) {
			state = payShopCart(list.get(i));
			if (state == 1)
				state_b = 1;
			if (state == 0)
				delShopCartMethod(list.get(i).getId());
		}
		return state_b;
	}

	// 返回值---0 支付成功---1 商品库存不够
	public int payShopCart(ShopCart shopCart) {
		int result = 0;
		try {
			conn = connDB.connDB();
			String sql = "select stock from goods_shop where id = " + shopCart.getGoodId();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			if (shopCart.getNum() > rs.getInt(1))
				result = 1;
			else {
				String sqlG = "update goods_shop set stock = stock - " + shopCart.getNum() + " where id = "
						+ shopCart.getGoodId();
				psmt = conn.prepareStatement(sqlG);
				psmt.executeUpdate();
				String sqlB = "update user_shop set money = money - " + shopCart.totalPrice()
						+ "*(select offlv from user_shop where id = " + shopCart.getBuyerId() + ")*0.1 where id = "
						+ shopCart.getBuyerId();
				psmt = conn.prepareStatement(sqlB);
				psmt.executeUpdate();
				String sqlS = "update user_shop set money = money + " + shopCart.totalPrice()
						+ "*(select offlv from user_shop where id = " + shopCart.getSellerId() + ")*0.1 where id = "
						+ shopCart.getSellerId();
				psmt = conn.prepareStatement(sqlS);
				psmt.executeUpdate();
				result = 0;
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
}
