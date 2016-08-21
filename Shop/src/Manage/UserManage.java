package Manage;

import java.util.ArrayList;

import Entity.Goods;
import Entity.User;

public class UserManage {
	UserManageMethod umm = new UserManageMethod();

	// ��ʵ�û���������
	public int verify(String unm, String pwd) {
		int i = umm.verifyMethod(unm, pwd);
		switch (i) {
		case 0:
			System.out.println("����������û�");
			return 0;
		case 1:
			System.out.println("�������");
			return 0;
		case 2:
			return 1;
		default:
			return 0;
		}
	}

	// ��¼ע����Ϣ
	public int record(String unm, String pwd) {
		if (umm.selectUnm(unm) == 0) {
			umm.recordMethod(unm, pwd);
			return 0;
		} else
			return 1;

	}

	// ��ȡ�û���Ϣ
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
				admin = "���";
			if (user.getAdmin() == 2)
				admin = "����";
			if (user.getAdmin() == 3)
				admin = "������";
			userList.add("��ţ�" + user.getId() + "\t�û�����" + user.getUnm() + "\t\t���룺" + user.getPwd() + "\t\t��Ǯ��"
					+ user.getMoney() + "\t\t�ۿۣ�" + user.getlevel() + "\t\tȨ�ޣ�" + admin);
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
			goodsList.add("��ţ�" + good.getId() + "\t���ƣ�" + good.getName() + "\t\t��棺" + good.getStock() + "\t\t���ۣ�"
					+ good.getPrice() + "\t\t���" + good.getCategory() + "\t\t���ң�" + good.getOwnerId());
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