package Manage;

import java.util.ArrayList;

import Entity.Goods;
import Entity.User;
import Service.GoodsDB;
import Service.UserDB;

public class UserManageMethod {
	UserDB udb = new UserDB();
	GoodsDB gdb = new GoodsDB();

	// ��ʵ�û���������ķ���---0δ�ҵ�---1���벻��---2��ȷ
	public int verifyMethod(String unm, String pwd) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (User user : list) {
			if (user.getUnm().equals(unm)) {
				if (user.getPwd().equals(pwd))
					return 2;
				return 1;
			}
		}
		return 0;
	}

	// ��¼ע����Ϣ�ķ���
	public void recordMethod(String unm, String pwd) {
		User user = new User(udb.getLastId() + 1, unm, pwd, 1000, 10, 1);
		udb.addUser(user);
	}

	// ��ѯ�û����Ƿ��Ѵ���---0������---1�Ѵ���
	public int selectUnm(String unm) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (User user : list) {
			if (user.getUnm().equals(unm))
				return 1;
		}
		return 0;
	}

	// ��ȡ�û�����Ϣ
	public User getInfoMethod(String unm) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (User user : list) {
			if (user.getUnm().equals(unm))
				return user;
		}
		return null;
	}

	public String getInfoMethod(int id) {
		String admin = null;
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (User user : list) {
			if (user.getId() == id) {
				if (user.getAdmin() == 1)
					admin = "���";
				if (user.getAdmin() == 2)
					admin = "����";
				if (user.getAdmin() == 3)
					admin = "������";
				return "��ţ�" + user.getId() + "\t�û�����" + user.getUnm() + "\t\t���룺" + user.getPwd() + "\t\t���У�"
						+ user.getMoney() + "\t\t�ۿۣ�" + user.getlevel() + "\t\tȨ�ޣ�" + admin;
			}
		}
		return null;
	}

	public ArrayList<User> selUserMethod() {
		return udb.getAllUser();
	}

	public void delUserMethod(int userId) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == userId) {
				list.remove(i);
				break;
			}
		}
		udb.setAllUser(list);
	}

	public ArrayList<Goods> getGoodsMethod() {
		return gdb.getAllGoods();
	}

	public void delGoodsMethod(int userId) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == userId) {
				list.remove(i);
				break;
			}
		}
		gdb.setAllGoods(list);
	}

	public String getGoodsInfoMethod(int id) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = gdb.getAllGoods();
		for (Goods good : list) {
			if (good.getId() == id)
				return "��ţ�" + good.getId() + "\t���ƣ�" + good.getName() + "\t\t��棺" + good.getStock() + "\t\t���ۣ�"
						+ good.getPrice() + "\t\t���" + good.getCategory() + "\t\t���ң�" + good.getOwnerId();
		}
		return null;
	}

	public String getLevelMethod(int id) {
		String admin = null;
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (User user : list) {
			if (user.getId() == id) {
				if (user.getAdmin() == 1)
					admin = "���";
				if (user.getAdmin() == 2)
					admin = "����";
				if (user.getAdmin() == 3)
					admin = "������";
				return "��ţ�" + user.getId() + "\t�û�����" + user.getUnm() + "\t\t���룺" + user.getPwd() + "\t\t���У�"
						+ user.getMoney() + "\t\t�ۿۣ�" + user.getlevel() + "\t\tȨ�ޣ�" + admin;
			}
		}
		return null;
	}

	public void chgLevelMethod(int id, int level) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setLv(level);
				break;
			}
		}
		udb.setAllUser(list);
	}

	public void chgAdminMethod(int id, int level) {
		ArrayList<User> list = new ArrayList<User>();
		list = udb.getAllUser();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setAdmin(level);
				break;
			}
		}
		udb.setAllUser(list);
	}
}