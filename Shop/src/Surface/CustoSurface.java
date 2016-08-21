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
	private User user; // ������˻�
	private int optNum; // ѡ�������
	private int selNum; // ��ѯ������
	private int buyNum; // ������Ʒ�ı��
	private int payNum; // ���ﳵ�����ı��

	public CustoSurface(User user) {
		this.user = user;
	}

	public void show() {
		System.out.println("-------------------------------------------------------");
		System.out.println("��ӭ�����˺�С����");
		System.out.println("��ѡ�⣺\t1.��һȦ\t\t2.�ӿ���\t\t3.ȥǰ̨\t\t4.����\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
			System.out.println("�����񲻺���");
		}
		show();
	}

	public void select() {
		System.out.println("-------------------------------------------------------");
		System.out.println("��ѡ�⣺\t1.�������\t\t2.�������\t\t3.��������\t\t4.���ٿ���\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
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
			System.out.println("��������ī��");
			return;
		case 0:
			System.exit(0);
		default:
			System.out.println("�����񲻺���");
		}
		select();
	}

	public void buy() {
		String goodInfo;
		String wan_b; // �Ƿ���ȷ
		String con_b; // �Ƿ����
		System.out.print("��Ҫɶ��������֪���Ͱ�0��ȥ�ң���");
		buyNum = in.nextInt();
		if (buyNum == 0)
			return;
		goodInfo = gm.getGoodInfo(buyNum);
		if (goodInfo == null)
			return;
		System.out.println(goodInfo);
		System.out.print("��Ҫ�������y/n��  ");
		wan_b = in.next();
		while ((!wan_b.equals("y")) && (!wan_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("�ӿ��ﲻ��y/n��");
			wan_b = in.next();
		}
		if (wan_b.equals("y")) {
			addShopCart(buyNum);
		}
		System.out.print("��Ҫ��y/n��");
		con_b = in.next();
		while ((!con_b.equals("y")) && (!con_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("��Ҫ��y/n��");
			con_b = in.next();
		}
		if (con_b.equals("y")) {
			buy();
		}
	}

	public void pay() {
		ArrayList<ShopCart> list = new ArrayList<>();
		System.out.println("-------------------------------------------------------");
		System.out.println("��������Щ������");
		list = scm.getMyShopCart(user);
		if (list.size() == 0)
			System.out.println("��������ǿյ�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". ���ƣ�" + list.get(i).getName() + "\t\t������" + list.get(i).getNum() + "\t\t���ۣ�"
					+ list.get(i).getPrice() + "\t\t�ܼۣ�" + list.get(i).totalPrice());
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("��ѡ�⣺\t1.��Ǯ\t\t2.��Ҫ��\t\t3.���ٿ���\t\t0.��ѧ");
		System.out.print("��Ҫ��ʲô��");
		payNum = in.nextInt();
		switch (payNum) {
		case 1:
			payShopCart(list);
			break;
		case 2:
			delShopCart(list);
			break;
		case 3:
			System.out.println("��������ī��");
			return;
		case 0:
			System.exit(0);
		default:
			System.out.println("�����񲻺���");
		}
		pay();
	}

	public void addShopCart(int id) {
		int num;
		System.out.print("��Ҫ������");
		num = in.nextInt();
		scm.addShopCart(id, user.getId(), num);
		System.out.println("���������");
	}

	public void delShopCart(ArrayList<ShopCart> list) {
		int del_n; // Ҫɾ������Ʒ���
		String del_b;
		System.out.print("�ĸ������㲻Ҫ�ˣ���0ȡ������");
		del_n = in.nextInt();
		while (del_n > list.size()) {
			System.out.println("����������û�������");
			System.out.print("�ĸ������㲻Ҫ�ˣ���0ȡ������");
			del_n = in.nextInt();
		}
		if (del_n == 0)
			return;
		System.out.print("����Ĳ�Ҫ�ˣ���y/n��");
		del_b = in.next();
		while ((!del_b.equals("y")) && (!del_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("����Ĳ�Ҫ�ˣ���y/n��");
			del_b = in.next();
		}
		if (del_b.equals("y")) {
			scm.delMyShopCart(list.get(del_n - 1).getId());
			System.out.println("��ô�õĶ��������Ȼ����Ҫ");
		} else {
			System.out.println("��������ī��");
		}
	}

	public void payShopCart(ArrayList<ShopCart> list) {
		String pay_b; // �Ƿ�֧��
		double totalP = 0; // �ܽ��
		double totalP_level = 0;
		int state; // �Ƿ������������
		for (ShopCart shopCart : list) {
			totalP += shopCart.totalPrice();
		}
		totalP_level = totalP * user.getlevel() * 0.1;
		System.out.println("Vip�ȼ���" + user.getlevel());
		System.out.println("�ܼۣ�" + totalP_level + "(" + totalP + "*0." + user.getlevel() + ")");
		System.out.print("���ڸ�Ǯ����y/n��");
		pay_b = in.next();
		while ((!pay_b.equals("y")) && (!pay_b.equals("n"))) {
			System.out.println("�����񲻺���");
			System.out.print("���ڸ�Ǯ����y/n��");
			pay_b = in.next();
		}
		if (pay_b.equals("y")) {
			if (user.getMoney() < totalP_level) {
				System.out.println("����Ų���Ǯ��");
				return;
			}
			state = scm.payShopCart(list);
			if (state == 0)
				System.out.println("������Ķ����߰�");
			if (state == 1)
				System.out.println("�ж���û���ˣ���Ķ�����Ǯ��");
		} else {
			System.out.println("��������ī��");
		}
	}
}