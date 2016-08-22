package Surface;

import Entity.User;

public class Client {

	public static void main(String[] args) {
		LoginSurface loginSf = new LoginSurface();
		AdminSurface adminSf = new AdminSurface();
		SelleSurface selleSf;
		CustoSurface custoSf;
		User user_log; // ������˻�
		int cus_b = 1; // �Ƿ�������ϵͳ

		System.out.println("------||||||--------------------------------||||||------");
		System.out.println("||||||--------------------------------------------||||||");
		System.out.println("------||||||----------����Сѧ�˺�С����----------||||||------");
		System.out.println("||||||--------------------------------------------||||||");
		System.out.println("------||||||--------------------------------||||||------");
		System.out.println("");
		System.out.println("��ʾ��������Ӧ����" + "\n");
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
