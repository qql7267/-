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
			System.out.println("输入错误，请重新输入");
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
		System.out.println("选项：\t1.用户管理\t\t2.商品管理\t\t3.退出登入\t\t0.退出");
		System.out.print("请选择：");
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
		System.out.println("选项：\t1.用户列表\t\t2.更改折扣\t\t3.更改权限\t\t4.删除用户\t\t5.返回\t\t0.退出");
		System.out.print("请选择：");
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
		System.out.println("选项：\t1.商品列表\t\t2.删除商品\t\t3.返回\t\t0.退出");
		System.out.print("请选择：");
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
		System.out.println("用户列表：");
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
		int del_res; // 删除结果
		System.out.print("请输入要删除的用户编号：");
		del_num = in.nextInt();
		String userInfo = um.getInfo(del_num);
		if (userInfo == null) {
			System.out.println("不存在这个用户");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("是否正确（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			del_res = um.delUser(del_num);
			if (del_res == 1)
				System.out.println("已删除该用户");
			if (del_res == 2)
				System.out.println("该用户有商品在出售，暂时不能删除");
			if (del_res == 3)
				System.out.println("不能删除你自己");
		}
		System.out.print("是否继续（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否继续（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delUser();
		}
	}

	public void selGoods() {
		System.out.println("-------------------------------------------------------");
		System.out.println("商品列表：");
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
		System.out.print("请输入要删除的商品编号：");
		del_num = in.nextInt();
		String userInfo = um.getGoodsInfo(del_num);
		if (userInfo == null) {
			System.out.println("不存在这个商品");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("是否正确（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			um.delGoods(del_num);
			System.out.println("删除成功");
		}
		System.out.print("是否继续（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否删除（y/n）");
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
		System.out.print("请输入要更改的用户编号：");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (userInfo == null) {
			System.out.println("不存在这个用户");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("是否正确（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.print("要更改成的折扣等级（折扣等级范围1-10）：");
			level = in.nextInt();
			while (level > 10 || level < 1) {
				System.out.println("输入错误，请重新输入");
				System.out.print("要更改成的折扣等级（折扣等级范围1-10）：");
				level = in.nextInt();
			}
			um.chgLevel(chg_num, level);
			System.out.println("更改完成");
		}
		System.out.print("是否继续（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否继续（y/n）");
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
		System.out.print("请输入要更改的用户编号：");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (chg_num == 0) {
			System.out.println("不可以修改自己");
			return;
		}
		if (userInfo == null) {
			System.out.println("不存在这个用户");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("是否正确（y/n）  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否正确（y/n）");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.println("权限等级：1.买家 2.卖家 3.管理员(暂时关闭修改)");
			System.out.print("要更成的权限等级：");
			level = in.nextInt();
			while (level > 2 || level < 1) {
				System.out.println("输入错误，请重新输入");
				System.out.print("要更成的权限等级：");
				level = in.nextInt();
			}
			um.chgAdmin(chg_num, level);
			System.out.println("更改完成");
		}
		System.out.print("是否继续（y/n）");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("输入错误，请重新输入");
			System.out.print("是否继续（y/n）");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgAdmin();
		}
	}
}
