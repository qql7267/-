package Surface;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.ShopCart;
import Entity.User;
import Manage.GoodsManage;
import Manage.ShopCartManage;

public class CustoSurface {
	private GoodsManage gm = new GoodsManage();
	private ShopCartManage scm = new ShopCartManage();
	private Scanner in = new Scanner(System.in);
	private User user; // 登入的账户
	private int optNum; // 选择的数字
	private int selNum; // 查询的数字
	private int buyNum; // 购买商品的编号
	private int payNum; // 购物车操作的编号

	public CustoSurface(User user) {
		this.user = user;
	}

	public void show() {
		System.out.println("-------------------------------------------------------");
		System.out.println("欢迎来到八号小卖店");
		System.out.println("多选题：\t1.逛一圈\t\t2.扔筐里\t\t3.去前台\t\t4.变身\t\t0.逃学");
		System.out.print("你要干什么：");
		optNum = in.nextInt();
		switch (optNum) {
		case 1:
			select();
			break;
		case 2:
			buy();
			break;
		case 3:
			pay();
			break;
		case 4:
			return;
		case 0:
			System.exit(0);
		default:
			System.out.println("你眼神不好吗");
		}
		show();
	}

	public void select() {
		System.out.println("-------------------------------------------------------");
		System.out.println("多选题：\t1.按店家找\t\t2.按类别找\t\t3.按名称找\t\t4.我再看看\t\t0.逃学");
		System.out.print("你要干什么：");
		selNum = in.nextInt();
		switch (selNum) {
		case 1:
			gm.selectGoods_Seller();
			break;
		case 2:
			gm.selectGoods_Category();
			break;
		case 3:
			gm.selectGoods_Name();
			break;
		case 4:
			System.out.println("你这人真墨迹");
			return;
		case 0:
			System.exit(0);
		default:
			System.out.println("你眼神不好吗");
		}
		select();
	}

	public void buy() {
		String goodInfo;
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.print("你要啥东西（不知道就按0回去找）：");
		buyNum = in.nextInt();
		if (buyNum == 0)
			return;
		goodInfo = gm.getGoodInfo(buyNum);
		if (goodInfo == null)
			return;
		System.out.println(goodInfo);
		System.out.print("你要这个？（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("你眼神不好吗");
			System.out.print("扔筐里不（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			addShopCart(buyNum);
		}
		System.out.print("还要吗（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("你眼神不好吗");
			System.out.print("还要吗（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			buy();
		}
	}

	public void pay() {
		ArrayList<ShopCart> list = new ArrayList<>();
		System.out.println("-------------------------------------------------------");
		System.out.println("筐里有这些东西：");
		list = scm.getMyShopCart(user);
		if (list.size() == 0)
			System.out.println("你这个筐是空的");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". 名称：" + list.get(i).getName() + "\t\t数量：" + list.get(i).getNum() + "\t\t单价："
					+ list.get(i).getPrice() + "\t\t总价：" + list.get(i).totalPrice());
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("多选题：\t1.给钱\t\t2.不要了\t\t3.我再看看\t\t0.逃学");
		System.out.print("你要干什么：");
		payNum = in.nextInt();
		switch (payNum) {
		case 1:
			payShopCart(list);
			break;
		case 2:
			delShopCart(list);
			break;
		case 3:
			System.out.println("你这人真墨迹");
			return;
		case 0:
			System.exit(0);
		default:
			System.out.println("你眼神不好吗");
		}
		pay();
	}

	public void addShopCart(int id) {
		int num;
		System.out.print("你要几个：");
		num = in.nextInt();
		scm.addShopCart(id, user.getId(), num);
		System.out.println("扔你筐里了");
	}

	public void delShopCart(ArrayList<ShopCart> list) {
		int del_n; // 要删除的商品编号
		String del_b;
		System.out.print("哪个东西你不要了（按0取消）：");
		del_n = in.nextInt();
		while (del_n > list.size()) {
			System.out.println("你是智障吗，没这个东西");
			System.out.print("哪个东西你不要了（按0取消）：");
			del_n = in.nextInt();
		}
		if (del_n == 0)
			return;
		System.out.print("你真的不要了？（y/n）");
		del_b = in.next();
		while ((!del_b.equals("y")) && (!del_b.equals("n"))) {
			System.out.println("你眼神不好吗");
			System.out.print("你真的不要了？（y/n）");
			del_b = in.next();
		}
		if (del_b.equals("y")) {
			scm.delMyShopCart(list.get(del_n - 1).getId());
			System.out.println("这么好的东西，你居然都不要");
		} else {
			System.out.println("你这人真墨迹");
		}
	}

	public void payShopCart(ArrayList<ShopCart> list) {
		String pay_b; // 是否支付
		double totalP = 0; // 总金额
		double totalP_level = 0;
		int state; // 是否存在数量不足
		for (ShopCart shopCart : list) {
			totalP += shopCart.totalPrice();
		}
		totalP_level = totalP * user.getlevel() * 0.1;
		System.out.println("Vip等级：" + user.getlevel());
		System.out.println("总价：" + totalP_level + "(" + totalP + "*0." + user.getlevel() + ")");
		System.out.print("现在给钱？（y/n）");
		pay_b = in.next();
		while ((!pay_b.equals("y")) && (!pay_b.equals("n"))) {
			System.out.println("你眼神不好吗");
			System.out.print("现在给钱？（y/n）");
			pay_b = in.next();
		}
		if (pay_b.equals("y")) {
			if (user.getMoney() < totalP_level) {
				System.out.println("你出门不带钱吗");
				return;
			}
			state = scm.payShopCart(list);
			if (state == 0)
				System.out.println("拿着你的东西走吧");
			if (state == 1)
				System.out.println("有东西没货了，别的都收完钱了");
		} else {
			System.out.println("你这人真墨迹");
		}
	}
}