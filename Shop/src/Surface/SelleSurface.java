package Surface;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.User;
import Manage.GoodsManage;

public class SelleSurface {
	private GoodsManage gm = new GoodsManage();
	private Scanner in = new Scanner(System.in);
	private String sel_b; // �Ƿ��������
	private int optNum; // ѡ�������
	private User user; // ������˻�

	public SelleSurface(User user) {
		this.user = user;
	}

	public int show() {
		System.out.println("��⵽����\n�Ƿ�������ģʽ��y/n��");
		sel_b = in.next();
		while ((!sel_b.equals("y")) && (!sel_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("��⵽����\n�Ƿ�������ģʽ��y/n��");
			sel_b = in.next();
		}
		if (sel_b.equals("y")) {
			return sellerManage();
		}
		return 1;
	}

	public int sellerManage() {
		System.out.println("-------------------------------------------------------");
		System.out.println("��ѡ�⣺\t1.�����ϼ�\t\t2.���䵹��\t\t3.�����ⷿ\t\t4.�����¼�\t\t5.����\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
		System.out.println("ɶ������Ҫ�ϼܰ���");
		System.out.print("���ƣ�");
		name = in.next();
		System.out.print("������");
		stock = in.nextInt();
		System.out.print("���ۣ�");
		price = in.nextDouble();
		System.out.print("���");
		category = in.next();
		gm.addGoods(name, stock, price, category, user.getId());
		System.out.println("���Ӳֿ�����");
	}

	public void selGoods() {
		ArrayList<String> list = new ArrayList<>();
		System.out.println("�����еļҵ���");
		list = gm.selGoods(user.getId());
		for (String goodsList : list)
			System.out.println(goodsList);
	}

	public void chgGoods() {
		int chg_num; // �޸ĵ���Ʒ���
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.println("-------------------------------------------------------");
		System.out.println("����Ūɶ�۶���");
		System.out.print("�ĸ�������Ҫ��");
		chg_num = in.nextInt();
		String goodInfo = gm.getGoodInfo(chg_num, user.getId());
		if (goodInfo == null) {
			System.out.println("�㿴�������ⶫ����");
			return;
		} else
			System.out.println(goodInfo);
		System.out.print("���������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("���������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			gm.chgGoods(chg_num);
			System.out.println("������");
		} else {
			System.out.println("��������ī��");
		}
		System.out.print("��Ū����?��y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("��Ū����?��y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			chgGoods();
		}
	}

	public void delGoods() {
		int del_num; // ɾ������Ʒ���
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.println("-------------------------------------------------------");
		System.out.print("ɶ������Ҫ�ˣ�");
		del_num = in.nextInt();
		String goodInfo = gm.getGoodInfo(del_num, user.getId());
		if (goodInfo == null) {
			System.out.println("�㿴�������ⶫ����");
			return;
		} else
			System.out.println(goodInfo);
		System.out.print("���������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("���������y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			gm.delGoods(del_num);
			System.out.println("����");
		} else {
			System.out.println("��������ī��");
		}
		System.out.print("����ɶ����y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("����ɶ����y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			delGoods();
		}
	}
}