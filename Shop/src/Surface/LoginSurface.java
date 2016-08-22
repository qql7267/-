package Surface;

import java.util.Scanner;

import Entity.User;
import Manage.UserManage;

public class LoginSurface {
	private Scanner in = new Scanner(System.in);
	private UserManage um = new UserManage();
	private String username;
	private String password;
	private int optNum; // ѡ�������
	private int log_b = 0; // �Ƿ����ɹ�
	private int reg_b = 0; // �Ƿ�ע��ɹ�
	private User user;

	public User show() {
		log_b = 0;
		while (log_b == 0) {
			System.out.println("-------------------------------------------------------");
			System.out.println("ѡ�\t1.����\t\t2.ע��\t\t0.�˳�");
			System.out.print("��Ҫ��ʲô��");
			optNum = in.nextInt();
			switch (optNum) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 0:
				System.out.println("��ӭ����");
				System.exit(0);
			default:
				System.out.println("�����������������");
			}
		}
		System.out.println("-------------------------------------------------------");
		user = um.getInfo(username);
		System.out.println(" ��ӭ������" + username);
		return user;
	}

	public void login() {
		System.out.print("�������û�����");
		username = in.next();
		System.out.print("���������룺");
		password = in.next();
		log_b = um.verify(username, password);
	}

	public void register() {
		String password_again;
		System.out.println("��ʼע��");
		System.out.print("�������û�����2-12λ����");
		username = in.next();
		if (username.length() < 13 && username.length() > 1) {
			System.out.print("���������루3-12λ����");
			password = in.next();
			if (password.length() < 13 && password.length() > 2) {
				System.out.print("���ٴ��������룺");
				password_again = in.next();
				if (password.equals(password_again)) {
					if (um.record(username, password) == 0) {
						reg_b = 1;
						if (reg_b == 1) {
							System.out.println("ע��ɹ�");
							System.out.println("�Ƿ���룿��y/n��");
							if (in.next().equals("y")) {
								log_b = 1;
							}
						}
					} else {
						System.out.println("�Ѵ��ڴ��û���\n���������룺");
					}
				} else {
					System.out.println("�������벻ͬ\n���������룺");
				}
			} else {
				System.out.println("���볤�Ȳ���ȷ\n���������룺");
			}
		} else {
			System.out.println("�û������Ȳ���ȷ\n���������룺");
		}
	}
}
