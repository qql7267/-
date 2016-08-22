package Surface;

import Entity.User;

public class Client {

	public static void main(String[] args) {
		LoginSurface loginSf = new LoginSurface();
		AdminSurface adminSf = new AdminSurface();
		SelleSurface selleSf;
		CustoSurface custoSf;
		User user_log; // 登入的账户
		int cus_b = 1; // 是否进入买家系统

		System.out.println("------||||||--------------------------------||||||------");
		System.out.println("||||||--------------------------------------------||||||");
		System.out.println("------||||||----------断罪小学八号小卖部----------||||||------");
		System.out.println("||||||--------------------------------------------||||||");
		System.out.println("------||||||--------------------------------||||||------");
		System.out.println("");
		System.out.println("提示：输入相应数字" + "\n");
		while (true) {
			user_log = loginSf.show();
			custoSf = new CustoSurface(user_log);
			selleSf = new SelleSurface(user_log);
			if (user_log.getAdmin() == 3)
				cus_b = adminSf.show();
			if (user_log.getAdmin() == 2)
				cus_b = selleSf.show();
			if (cus_b == 1)
				custoSf.show();
			cus_b = 1;
		}
	}
}
