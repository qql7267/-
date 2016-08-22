package Manage;

import java.util.ArrayList;

import Entity.Goods;
import Entity.ShopCart;
import Entity.User;
import Service.GoodsDB;
import Service.ShopCartDB;
import Service.UserDB;

public class GoodsManageMethod {
	GoodsDB gdb = new GoodsDB();
	UserDB udb = new UserDB();
	ShopCartDB scdb = new ShopCartDB();

	public ArrayList<String> selectGoodsMethod_Seller(int selName) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> goodList = new ArrayList<String>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getOwnerId() == selName)
				goodList.add("商品编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice() + "\t\t类别：" + good.getCategory() + "\t\t店家：" + good.getOwnerId());
		}
		return goodList;
	}

	public ArrayList<String> selectGoodsMethod_Category(String selName) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> goodList = new ArrayList<String>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getCategory().contains(selName))
				goodList.add("编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice() + "\t\t类别：" + good.getCategory() + "\t\t店家：" + good.getOwnerId());
		}
		return goodList;
	}

	public ArrayList<String> selectGoodsMethod_Name(String selName) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> goodList = new ArrayList<String>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getName().contains(selName))
				goodList.add("编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice() + "\t\t类别：" + good.getCategory() + "\t\t店家：" + good.getOwnerId());
		}
		return goodList;
	}

	public int[] getList_Seller() {
		int[] sellerlist = new int[1000];
		ArrayList<User> list = new ArrayList<>();
		list = udb.getAllUser();
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAdmin() == 2)
				sellerlist[j++] = list.get(i).getId();
		}
		return sellerlist;
	}

	public ArrayList<String> getList_Category() {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> categoryList = new ArrayList<String>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			int i = 1;
			for (String categ : categoryList) {
				if (categ.equals(good.getCategory()))
					i = 0;
			}
			if (i == 1)
				categoryList.add(good.getCategory());
		}
		return categoryList;
	}

	public String getGoodInfoMethod(int id) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getId() == id)
				return "编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice();
		}
		return null;
	}

	public String getGoodInfoMethod(int id, int ownerId) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getId() == id && good.getOwnerId() == ownerId)
				return "编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice();
		}
		return null;
	}

	public void addGoodsMethod(String name, int stock, double price, String category, int ownerId) {
		Goods good = new Goods(gdb.getLastId() + 1, name, stock, price, category, ownerId);
		gdb.addGoods(good);
	}

	public ArrayList<String> selGoodMethod(int ownerId) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> goodsList = new ArrayList<>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getOwnerId() == ownerId)
				goodsList.add("编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice());
		}
		return goodsList;
	}

	public String getGoodsMethod(int goodId) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getId() == goodId)
				return "编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
						+ good.getPrice();
		}
		return null;
	}

	public void chgGoodsMethod(int goodId, String name, int stock, double price, String category) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getId() == goodId) {
				good.setName(name);
				good.setStock(stock);
				good.setPrice(price);
				good.setCategory(category);
				break;
			}
		}
		gdb.setAllGoods(list);
	}

	public void delGoodsMethod(int goodId) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<ShopCart> listShopCart = new ArrayList<>();
		list = gdb.getAllGoods();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == goodId) {
				list.remove(i);
				break;
			}
		}
		gdb.setAllGoods(list);
		listShopCart = scdb.getAllShopCar();
		for (int i = 0; i < listShopCart.size(); i++) {
			if (listShopCart.get(i).getGoodId() == goodId) {
				listShopCart.remove(i);
				i--;
			}
		}
		scdb.setAllShopCar(listShopCart);
	}
}
