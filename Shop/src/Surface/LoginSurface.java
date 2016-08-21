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
			System.out.println("��ѡ�⣺\t1.�߽�����\t\t2.��������\t\t0.��Ҫ��ѧ");
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
				System.out.println("����ߣ������ſڼ�");
				System.exit(0);
			default:
				System.out.println("�����񲻺���");
			}
		}
		System.out.println("-------------------------------------------------------");
		user = um.getInfo(username);
		if (user.getId() == 0)
			System.out.println("���ã������죬��ӭ���� ����������");
		else
			System.out.println(" ��ӭ������" + username + "Сѧ��  " + "����Ц����");
		return user;
	}

	public void login() {
		System.out.print("��������ǩ�ϣ�");
		username = in.next();
		System.out.print("˵���ţ�");
		password = in.next();
		log_b = um.verify(username, password);
	}

	public void register() {
		String password_again;
		System.out.println("��ͬѧ��������д��������");
		System.out.print("����д�����֣�2-12λ����");
		username = in.next();
		if (username.length() < 13 && username.length() > 1) {
			System.out.print("����д���ţ�3-12λ����");
			password = in.next();
			if (password.length() < 13 && password.length() > 2) {
				System.out.print("��˵�鰵�ţ�");
				password_again = in.next();
				if (password.equals(password_again)) {
					if (um.record(username, password) == 0) {
						reg_b = 1;
						if (reg_b == 1) {
							System.out.println("�����ھ��Ƕ���Сѧ���ڻ���");
							System.out.println("���ھ��߽����ţ���y/n��");
							if (in.next().equals("y")) {
								log_b = 1;
							}
						}
					} else {
						System.out.println("����������\n������дһ��");
					}
				} else {
					System.out.println("���������𣬰��Ŷ���һ��\n������дһ��");
				}
			} else {
				System.out.println("������������ô�̵İ���\n������дһ��");
			}
		} else {
			System.out.println("������������ô�̵�����\n������дһ��");
		}
	}
}
