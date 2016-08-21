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
			System.out.println("多选题：\t1.走进大门\t\t2.新生报到\t\t0.我要逃学");
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
				System.out.println("你别走，咱们门口见");
				System.exit(0);
			default:
				System.out.println("你眼神不好吗");
			}
		}
		System.out.println("-------------------------------------------------------");
		user = um.getInfo(username);
		if (user.getId() == 0)
			System.out.println("您好，赵日天，欢迎回来 【恭敬脸】");
		else
			System.out.println(" 欢迎回来，" + username + "小学弟  " + "【奸笑脸】");
		return user;
	}

	public void login() {
		System.out.print("把你名字签上：");
		username = in.next();
		System.out.print("说暗号：");
		password = in.next();
		log_b = um.verify(username, password);
	}

	public void register() {
		String password_again;
		System.out.println("新同学啊，先来写个卖身契");
		System.out.print("这里写你名字（2-12位）：");
		username = in.next();
		if (username.length() < 13 && username.length() > 1) {
			System.out.print("这里写暗号（3-12位）：");
			password = in.next();
			if (password.length() < 13 && password.length() > 2) {
				System.out.print("再说遍暗号：");
				password_again = in.next();
				if (password.equals(password_again)) {
					if (um.record(username, password) == 0) {
						reg_b = 1;
						if (reg_b == 1) {
							System.out.println("你现在就是断罪小学的炮灰了");
							System.out.println("现在就走进大门？（y/n）");
							if (in.next().equals("y")) {
								log_b = 1;
							}
						}
					} else {
						System.out.println("不允许重名\n重新再写一份");
					}
				} else {
					System.out.println("你是智障吗，暗号都不一样\n重新再写一份");
				}
			} else {
				System.out.println("你是智障吗，这么短的暗号\n重新再写一份");
			}
		} else {
			System.out.println("你是智障吗，这么短的名字\n重新再写一份");
		}
	}
}
