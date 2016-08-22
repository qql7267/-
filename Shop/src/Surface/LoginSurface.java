package Surface;

import java.util.Scanner;

import Entity.User;
import Manage.UserManage;

public class LoginSurface {
	private Scanner in = new Scanner(System.in);
	private UserManage um = new UserManage();
	private String username;
	private String password;
	private int optNum; // 选择的数字
	private int log_b = 0; // 是否登入成功
	private int reg_b = 0; // 是否注册成功
	private User user;

	public User show() {
		log_b = 0;
		while (log_b == 0) {
			System.out.println("-------------------------------------------------------");
			System.out.println("选项：\t1.登入\t\t2.注册\t\t0.退出");
			System.out.print("你要干什么：");
			optNum = in.nextInt();
			switch (optNum) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 0:
				System.out.println("欢迎再来");
				System.exit(0);
			default:
				System.out.println("输入错误，请重新输入");
			}
		}
		System.out.println("-------------------------------------------------------");
		user = um.getInfo(username);
		System.out.println(" 欢迎回来，" + username);
		return user;
	}

	public void login() {
		System.out.print("请输入用户名：");
		username = in.next();
		System.out.print("请输入密码：");
		password = in.next();
		log_b = um.verify(username, password);
	}

	public void register() {
		String password_again;
		System.out.println("开始注册");
		System.out.print("请输入用户名（2-12位）：");
		username = in.next();
		if (username.length() < 13 && username.length() > 1) {
			System.out.print("请输入密码（3-12位）：");
			password = in.next();
			if (password.length() < 13 && password.length() > 2) {
				System.out.print("请再次输入密码：");
				password_again = in.next();
				if (password.equals(password_again)) {
					if (um.record(username, password) == 0) {
						reg_b = 1;
						if (reg_b == 1) {
							System.out.println("注册成功");
							System.out.println("是否登入？（y/n）");
							if (in.next().equals("y")) {
								log_b = 1;
							}
						}
					} else {
						System.out.println("已存在此用户名\n请重新输入：");
					}
				} else {
					System.out.println("两次密码不同\n请重新输入：");
				}
			} else {
				System.out.println("密码长度不正确\n请重新输入：");
			}
		} else {
			System.out.println("用户名长度不正确\n请重新输入：");
		}
	}
}
