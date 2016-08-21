package Surface;

import java.util.ArrayList;
import java.util.Scanner;

import Manage.UserManage;

public class AdminSurface {
	private UserManage um = new UserManage();
	private Scanner in = new Scanner(System.in);
	private String adm_b; // �Ƿ�������Ա
	private int optNum; // ѡ�������

	// ����ֵ---1 ����---0������
	public int show() {
		System.out.println("��⵽����Ա\n�Ƿ�������Աģʽ��y/n��");
		adm_b = in.next();
		while ((!adm_b.equals("y")) && (!adm_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("��⵽����Ա\n�Ƿ�������Աģʽ��y/n��");
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
		System.out.println("��ѡ�⣺\t1.�������̴���\t2.���ǽ��񴦵�\t3.����\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
		System.out.println("��ѡ�⣺\t1.ѧ����\t\t2.���ۿ�\t\t3.��Ȩ��\t\t4.����ѧ��\t\t5.����\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
		System.out.println("��ѡ�⣺\t1.��Ʒ��\t\t2.ɾ��Ʒ\t\t3.����\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
		System.out.println("���е�ѧ����");
		ArrayList<String> list = new ArrayList<>();
		list = um.selUser();
		for (String userInfo : list) {
			System.out.println(userInfo);
		}
	}

	public void delUser() {
		int del_num; // ɾ������Ʒ���
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.print("����˭��д��˭��");
		del_num = in.nextInt();
		String userInfo = um.getInfo(del_num);
		if (userInfo == null) {
			System.out.println("���˲��Ƕ���Сѧ��");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("�����������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("�����������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			um.delUser(del_num);
			System.out.println("����Ҳ���Ƕ���Сѧ������");
		}
		System.out.print("��Ҫ����˭��y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("��Ҫ����˭��y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delUser();
		}
	}

	public void selGoods() {
		System.out.println("-------------------------------------------------------");
		System.out.println("���е���Ʒ��");
		ArrayList<String> list = new ArrayList<>();
		list = um.selGoods();
		for (String goodsInfo : list) {
			System.out.println(goodsInfo);
		}
	}

	public void delGoods() {
		int del_num; // ɾ������Ʒ���
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.print("Ҫ���ĸ���");
		del_num = in.nextInt();
		String userInfo = um.getGoodsInfo(del_num);
		if (userInfo == null) {
			System.out.println("��������Ѿ���û��");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("���������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("���������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			um.delGoods(del_num);
			System.out.println("�ӳ�ȥ��");
		}
		System.out.print("��Ҫ����y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("��Ҫ����y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delGoods();
		}
	}

	public void chgLevel() {
		int level = 0;
		int chg_num; // ���ĵ��û����
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.print("Ҫ���ĸ�ѧ����");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (userInfo == null) {
			System.out.println("����˲��Ƕ���Сѧ��");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("�����������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("�����������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.print("Ҫ���ɵ��ۿ۵ȼ���");
			level = in.nextInt();
			while (level > 10 || level < 1) {
				System.out.println("�ۿ۵ȼ���Χ1-10");
				System.out.print("����Ҫ���ĵ��ۿ۵ȼ���");
				level = in.nextInt();
			}
			um.chgLevel(chg_num, level);
			System.out.println("������");
		}
		System.out.print("������y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("������y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgLevel();
		}
	}

	public void chgAdmin() {
		int level = 0;
		int chg_num; // ���ĵ��û����
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.print("Ҫ���ĸ�ѧ����");
		chg_num = in.nextInt();
		String userInfo = um.getUserLevel(chg_num);
		if (userInfo == null) {
			System.out.println("����˲��Ƕ���Сѧ��");
			return;
		} else
			System.out.println(userInfo);
		System.out.print("�����������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("�����������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			System.out.println("Ȩ�޵ȼ���1.��� 2.���� 3.�����죨�����㣩");
			System.out.print("Ҫ���ɵ�Ȩ�޵ȼ���");
			level = in.nextInt();
			while (level > 2 || level < 1) {
				System.out.print("Ҫ���ɵ�Ȩ�޵ȼ���");
				level = in.nextInt();
			}
			um.chgAdmin(chg_num, level);
			System.out.println("������");
		}
		System.out.print("������y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�������");
			System.out.print("������y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgAdmin();
		}
	}
}
