package Surface;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.User;
import Manage.GoodsManage;

public class SelleSurface {
	private GoodsManage gm = new GoodsManage();
	private Scanner in = new Scanner(System.in);
	private String sel_b; // 是否登入卖家
	private int optNum; // 选择的数字
	private User user; // 登入的账户

	public SelleSurface(User user) {
		this.user = user;
	}

	public int show() {
		System.out.println("检测到卖家\n是否开启卖家模式（y/n）");
		sel_b = in.next();
		while ((!sel_b.equals("y")) && (!sel_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("检测到卖家\n是否开启卖家模式（y/n）");
			sel_b = in.next();
		}
		if (sel_b.equals("y")) {
			return sellerManage();
		}
		return 1;
	}

	public int sellerManage() {
		System.out.println("-------------------------------------------------------");
		System.out.println("选项：\t1.添加商品\t\t2.查询商品\t\t3.调整商品\t\t4.删除商品\t\t5.退出登入\t\t0.退出");
		System.out.print("请选择：");
		optNum = in.nextInt();
		switch (optNum) {
		case 1:
			addGoods();
			break;
		case 2:
			selGoods();
			break;
		case 3:
			chgGoods();
			break;
		case 4:
			delGoods();
			break;
		case 5:
			return 0;
		case 0:
			System.exit(0);
		}
		sellerManage();
		return 0;
	}

	public void addGoods() {
		String name;
		int stock;
		double price;
		String category;
		System.out.println("-------------------------------------------------------");
		System.out.println("请输入商品信息：");
		System.out.print("名称：");
		name = in.next();
		System.out.print("数量：");
		stock = in.nextInt();
		System.out.print("单价：");
		price = in.nextDouble();
		System.out.print("类别：");
		category = in.next();
		gm.addGoods(name, stock, price, category, user.getId());
		System.out.println("添加成功");
	}

	public void selGoods() {
		ArrayList<String> list = new ArrayList<>();
		System.out.println("你拥有的商品：");
		list = gm.selGoods(user.getId());
		for (String goodsList : list)
			System.out.println(goodsList);
	}

	public void chgGoods() {
		int chg_num; // 修改的商品编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.println("-------------------------------------------------------");
		System.out.print("请输入要修改的商品编号：");
		chg_num = in.nextInt();
		String goodInfo = gm.getGoodInfo(chg_num, user.getId());
		if (goodInfo == null) {
			System.out.println("此商品不存在或非你所有");
			return;
		} else
			System.out.println(goodInfo);
		System.out.print("是否正确？（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确？（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			gm.chgGoods(chg_num);
			System.out.println("修改完成");
		}
		System.out.print("是否继续?（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否继续?（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgGoods();
		}
	}

	public void delGoods() {
		int del_num; // 删除的商品编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.println("-------------------------------------------------------");
		System.out.print("请输入要删除的商品编号：");
		del_num = in.nextInt();
		String goodInfo = gm.getGoodInfo(del_num, user.getId());
		if (goodInfo == null) {
			System.out.println("此商品不存在或非你所有");
			return;
		} else
			System.out.println(goodInfo);
		System.out.print("是否正确？（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确？（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			gm.delGoods(del_num);
			System.out.println("删除成功");
		}
		System.out.print("是否继续（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否继续（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delGoods();
		}
	}
}
