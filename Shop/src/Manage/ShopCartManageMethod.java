package Manage;

import java.util.ArrayList;

import Entity.Goods;
import Entity.ShopCart;
import Entity.User;
import Service.GoodsDB;
import Service.ShopCartDB;
import Service.UserDB;

public class ShopCartManageMethod {
	private ShopCartDB scdb = new ShopCartDB();
	private UserDB udb = new UserDB();
	private GoodsDB gdb = new GoodsDB();

	public ArrayList<ShopCart> getMyShopCartMethod(User user) {
		ArrayList<ShopCart> list = new ArrayList<>();
		ArrayList<ShopCart> shopCartList = new ArrayList<>();
		list = scdb.getAllShopCar();
		for (ShopCart shopCart : list) {
			if (shopCart.getBuyerId() == user.getId())
				shopCartList.add(shopCart);
		}
		return shopCartList;
	}

	public void addShopCartMethod(int goodId, int buyerId, int num) {
		String name = null;
		int sellerId = 0;
		double price = 0;
		ArrayList<Goods> goodsList = new ArrayList<>();
		goodsList = gdb.getAllGoods();
		for (Goods good : goodsList) {
			if (good.getId() == goodId) {
				name = good.getName();
				sellerId = good.getOwnerId();
				price = good.getPrice();
			}
		}
		int id = scdb.getLastId() + 1;
		ShopCart shopCart = new ShopCart(id, buyerId, sellerId, goodId, name, num, price);
		scdb.addShopCar(shopCart);
	}

	public void delShopCartMethod(int id) {
		ArrayList<ShopCart> list = new ArrayList<>();
		list = scdb.getAllShopCar();
		int i;
		for (i = 0; i < list.size(); i++)
			if (list.get(i).getId() == id)
				break;
		list.remove(i);
		scdb.setAllShopCar(list);
	}

	public int payAllShopCart(ArrayList<ShopCart> list) {
		int state_b = 0; // 是否存在数量不足--返回
		int state; // 数量是否足够--判定
		for (int i = 0; i < list.size(); i++) {
			state = payShopCart(list.get(i));
			if (state == 1) {
				state_b = 1;
			}
			if (state == 0)
				delShopCartMethod(list.get(i).getId());
		}
		return state_b;
	}

	// 返回值---0 支付成功---1 商品库存不够
	public int payShopCart(ShopCart shopCart) {
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<Goods> goodsList = new ArrayList<>();
		int buyer_p = 0, seller_p = 0, good_p = 0;
		userList = udb.getAllUser();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId() == shopCart.getBuyerId())
				buyer_p = i;
			if (userList.get(i).getId() == shopCart.getSellerId())
				seller_p = i;
		}
		goodsList = gdb.getAllGoods();
		for (int i = 0; i < goodsList.size(); i++) {
			if (goodsList.get(i).getId() == shopCart.getGoodId()) {
				good_p = i;
				break;
			}
		}
		if (goodsList.get(good_p).getStock() < shopCart.getNum())
			return 1;
		userList.get(buyer_p).setMoney(
				userList.get(buyer_p).getMoney() - shopCart.totalPrice() * userList.get(buyer_p).getlevel() * 0.1);
		userList.get(seller_p).setMoney(
				userList.get(seller_p).getMoney() + shopCart.totalPrice() * userList.get(buyer_p).getlevel() * 0.1);
		goodsList.get(good_p).setStock(goodsList.get(good_p).getStock() - shopCart.getNum());
		udb.setAllUser(userList);
		gdb.setAllGoods(goodsList);
		return 0;
	}
}
