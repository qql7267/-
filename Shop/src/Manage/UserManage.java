package Manage;

import java.util.ArrayList;

import Entity.Goods;
import Entity.User;

public class UserManage {
	UserManageMethod umm = new UserManageMethod();

	// 核实用户名和密码
	public int verify(String unm, String pwd) {
		int i = umm.verifyMethod(unm, pwd);
		switch (i) {
		case 0:
			System.out.println("不存在这个用户");
			return 0;
		case 1:
			System.out.println("密码错误");
			return 0;
		case 2:
			return 1;
		default:
			return 0;
		}
	}

	// 记录注册信息
	public int record(String unm, String pwd) {
		if (umm.selectUnm(unm) == 0) {
			umm.recordMethod(unm, pwd);
			return 0;
		} else
			return 1;

	}

	// 获取用户信息
	public User getInfo(String unm) {
		return umm.getInfoMethod(unm);
	}

	public String getInfo(int id) {
		return umm.getInfoMethod(id);
	}

	public String getGoodsInfo(int id) {
		return umm.getGoodsInfoMethod(id);
	}

	public ArrayList<String> selUser() {
		String admin = null;
		ArrayList<User> list = new ArrayList<User>();
		ArrayList<String> userList = new ArrayList<>();
		list = umm.selUserMethod();
		for (User user : list) {
			if (user.getAdmin() == 1)
				admin = "买家";
			if (user.getAdmin() == 2)
				admin = "卖家";
			if (user.getAdmin() == 3)
				admin = "赵日天";
			userList.add("编号：" + user.getId() + "\t用户名：" + user.getUnm() + "\t\t密码：" + user.getPwd() + "\t\t金钱："
					+ user.getMoney() + "\t\t折扣：" + user.getlevel() + "\t\t权限：" + admin);
		}
		return userList;
	}

	public void delUser(int userId) {
		umm.delUserMethod(userId);
	}

	public ArrayList<String> selGoods() {
		ArrayList<Goods> list = new ArrayList<Goods>();
		ArrayList<String> goodsList = new ArrayList<>();
		list = umm.getGoodsMethod();
		for (Goods good : list) {
			goodsList.add("编号：" + good.getId() + "\t名称：" + good.getName() + "\t\t库存：" + good.getStock() + "\t\t单价："
					+ good.getPrice() + "\t\t类别：" + good.getCategory() + "\t\t卖家：" + good.getOwnerId());
		}
		return goodsList;
	}

	public void delGoods(int goodId) {
		umm.delGoodsMethod(goodId);
	}

	public String getUserLevel(int userId) {
		return umm.getLevelMethod(userId);
	}

	public void chgLevel(int id, int level) {
		umm.chgLevelMethod(id, level);
	}

	public void chgAdmin(int id, int level) {
		umm.chgAdminMethod(id, level);
	}
}