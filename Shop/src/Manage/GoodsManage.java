package Manage;

import java.util.ArrayList;
import java.util.Scanner;

public class GoodsManage {
	private GoodsManageMethod gmm = new GoodsManageMethod();
	private Scanner in = new Scanner(System.in);

	public void selectGoods_Seller() {
		ArrayList<String> list = new ArrayList<String>();
		int[] sellerList = gmm.getList_Seller();
		for (int i = 0; i < sellerList.length; i++) {
			if (sellerList[i] != 0)
				System.out.print((i + 1) + "." + sellerList[i] + "\t");
			else
				break;
		}
		System.out.print("\n请选择：");
		list = gmm.selectGoodsMethod_Seller(sellerList[in.nextInt() - 1]);
		for (String info : list)
			System.out.println(info);
		if (list.size() == 0)
			System.out.println("该店家暂时没有商品要出售");
	}

	public void selectGoods_Category() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> categpryList = gmm.getList_Category();
		System.out.println("共有以下几种类别：");
		for (int i = 0; i < categpryList.size(); i++) {
			System.out.print((i + 1) + "." + categpryList.get(i) + "\t");
		}
		System.out.print("\n请选择：");
		list = gmm.selectGoodsMethod_Category(categpryList.get(in.nextInt() - 1));
		for (String info : list)
			System.out.println(info);
	}

	public void selectGoods_Name() {
		ArrayList<String> list = new ArrayList<String>();
		System.out.print("请输入要查询的部分或全部名称：");
		String selName = in.next();
		list = gmm.selectGoodsMethod_Name(selName);
		for (String info : list)
			System.out.println(info);
	}

	public String getGoodInfo(int id) {
		return gmm.getGoodInfoMethod(id);
	}

	public String getGoodInfo(int id, int ownerId) {
		return gmm.getGoodInfoMethod(id, ownerId);
	}

	public void addGoods(String name, int stock, double price, String category, int ownerId) {
		gmm.addGoodsMethod(name, stock, price, category, ownerId);
	}

	public ArrayList<String> selGoods(int ownberId) {
		return gmm.selGoodMethod(ownberId);
	}

	public String getGoods(int goodId) {
		return gmm.getGoodsMethod(goodId);
	}

	public void chgGoods(int goodId) {
		System.out.println("请先填写要修改的商品信息：");
		System.out.print("商品名称：");
		String name = in.next();
		System.out.print("商品数量：");
		int stock = in.nextInt();
		System.out.print("商品单价：");
		double price = in.nextDouble();
		System.out.print("商品类别：");
		String category = in.next();
		gmm.chgGoodsMethod(goodId, name, stock, price, category);
	}

	public void delGoods(int goodId) {
		gmm.delGoodsMethod(goodId);
	}
}
