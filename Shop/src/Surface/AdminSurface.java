package Surface;

import java.util.ArrayList;
import java.util.Scanner;

import Manage.UserManage;

public class AdminSurface {
	private UserManage um = new UserManage();
	private Scanner in = new Scanner(System.in);
	private String adm_b; // 是否登入管理员
	private int optNum; // 选择的数字

	// 返回值---1 进入---0不进入
	public int show() {
		System.out.println("检测到管理员\n是否开启管理员模式（y/n）");
		adm_b = in.next();
		while ((!adm_b.equals("y")) && (!adm_b.equals("n"))) {
			System.out.println("你眼神不好吗");
			System.out.print("检测到管理员\n是否开启管理员模式（y/n）");
			adm_b = in.next();
		}
		if (adm_b.equals("y")) {
			adminManage();
			return 0;
		}
		return 1;
	}

	public int adminManage() {
		System.out.println("-------------------------------------------------------");
		System.out.println("多选题：\t1.我是政教处的\t2.我是教务处的\t3.变身\t\t0.逃学");
		System.out.print("你要干什么：");
		optNum = in.nextInt();
		switch (optNum) {
		case 1:
			manageUser();
			break;
		case 2:
			manageGoods();
			break;
		case 3:
			return 0;
		case 0:
			System.exit(0);
		}
		adminManage();
		return 0;
	}

	public void manageUser() {
		int mu_num;
		System.out.println("-------------------------------------------------------");
		System.out.println("多选题：\t1.学生表\t\t2.改折扣\t\t3.改权限\t\t4.开除学生\t\t5.返回\t\t0.逃学");
		System.out.print("你要干什么：");
		mu_num = in.nextInt();
		switch (mu_num) {
		case 1:
			selUser();
			break;
		case 2:
			chgLevel();
			break;
		case 3:
			chgAdmin();
			break;
		case 4:
			delUser();
			break;
		case 5:
			return;
		case 0:
			System.exit(0);
		}
		manageUser();
	}

	public void manageGoods() {
		int ng_num;
		System.out.println("-------------------------------------------------------");
		System.out.println("多选题：\t1.商品表\t\t2.删商品\t\t3.返回\t\t0.逃学");
		System.out.print("你要干什么：");
		ng_num = in.nextInt();
		switch (ng_num) {
		case 1:
			selGoods();
			break;
		case 2:
			delGoods();
			break;
		case 3:
			return;
		case 0:
			System.exit(0);
		}
		manageGoods();
	}

	public void selUser() {
		System.out.println("-------------------------------------------------------");
		System.out.println("所有的学生：");
		ArrayList<String> list = new ArrayList<>();
		list = um.selUser();
		for (String userInfo : list) {
			System.out.println(userInfo);
		}
	}

	public void delUser() {
		int del_num; // 删除的商品编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.print("开除谁就写出谁：");
		del_num = in.nextInt();
		String userInfo = um.getInfo(del_num);
		if (userInfo == null) {
			System.out.println("这人不是断罪小学的");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("就是这个人吗（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("就是这个人吗（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			um.delUser(del_num);
			System.out.println("他再也不是断罪小学的人了");
		}
		System.out.print("还要开除谁吗（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("还要开除谁吗（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delUser();
		}
	}

	public void selGoods() {
		System.out.println("-------------------------------------------------------");
		System.out.println("所有的商品：");
		ArrayList<String> list = new ArrayList<>();
		list = um.selGoods();
		for (String goodsInfo : list) {
			System.out.println(goodsInfo);
		}
	}

	public void delGoods() {
		int del_num; // 删除的商品编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.print("要扔哪个：");
		del_num = in.nextInt();
		String userInfo = um.getGoodsInfo(del_num);
		if (userInfo == null) {
			System.out.println("这个东西已经扔没了");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("就是这个吗（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("就是这个吗（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			um.delGoods(del_num);
			System.out.println("扔出去了");
		}
		System.out.print("还要扔吗（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("还要扔吗（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delGoods();
		}
	}

	public void chgLevel() {
		int level = 0;
		int chg_num; // 更改的用户编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.print("要改哪个学生：");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (userInfo == null) {
			System.out.println("这个人不是断罪小学的");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("就是这个人吗（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("就是这个人吗（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.print("要更成的折扣等级：");
			level = in.nextInt();
			while (level > 10 || level < 1) {
				System.out.println("折扣等级范围1-10");
				System.out.print("输入要更改的折扣等级：");
				level = in.nextInt();
			}
			um.chgLevel(chg_num, level);
			System.out.println("改完了");
		}
		System.out.print("还改吗（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("还改吗（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgLevel();
		}
	}

	public void chgAdmin() {
		int level = 0;
		int chg_num; // 更改的用户编号
		String wan_b; // 是否正确
		String con_b; // 是否继续
		System.out.print("要改哪个学生：");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (userInfo == null) {
			System.out.println("这个人不是断罪小学的");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("就是这个人吗（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("就是这个人吗（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.println("权限等级：1.买家 2.卖家 3.赵日天（就是你）");
			System.out.print("要更成的权限等级：");
			level = in.nextInt();
			while (level > 2 || level < 1) {
				System.out.print("要更成的权限等级：");
				level = in.nextInt();
			}
			um.chgAdmin(chg_num, level);
			System.out.println("改完了");
		}
		System.out.print("还改吗（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错了");
			System.out.print("还改吗（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgAdmin();
		}
	}
}
