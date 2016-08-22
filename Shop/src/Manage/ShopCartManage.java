package Manage;

import java.util.ArrayList;

import Entity.ShopCart;
import Entity.User;

public class ShopCartManage {
	// private ShopCartManageMethod scmm = new ShopCartManageMethod();
	private ShopCartManageDBMethod scmmDB = new ShopCartManageDBMethod();

	public ArrayList<ShopCart> getMyShopCart(User user) {
		ArrayList<ShopCart> list = new ArrayList<>();
		// list = scmm.getMyShopCartMethod(user);
		list = scmmDB.getMyShopCartMethod(user);
		return list;
	}

	public void addShopCart(int goodid, int buyerId, int num) {
		// scmm.addShopCartMethod(goodid, buyerId, num);
		scmmDB.addShopCartMethod(goodid, buyerId, num);
	}

	public void delMyShopCart(int id) {
		// scmm.delShopCartMethod(id);
		scmmDB.delShopCartMethod(id);
	}

	public int payShopCart(ArrayList<ShopCart> list) {
		// return scmm.payAllShopCart(list);
		return scmmDB.payAllShopCart(list);
	}
}