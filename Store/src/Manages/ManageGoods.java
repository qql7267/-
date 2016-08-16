package Manages;

import Util.Goods;

public class ManageGoods {
	private ManageGoodsDBMethod mgmDB = new ManageGoodsDBMethod();
	// private ManageGoodsMethod mgm = new ManageGoodsMethod();

	public int addGood(Goods ngood) {
		// return mgm.addGoodMethod(ngood);
		if (mgmDB.selectName(ngood.getName()) == 1)
			return 1;
		if (mgmDB.selectNum(ngood.getNum()) == 1)
			return 2;
		mgmDB.addDB(ngood);
		return 0;
	}

	public String updGood(Goods ngood) {
		// Goods good = mgm.updGoodMethod(ngood);
		// return "���޸���Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" +
		// good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�" +
		// good.getPrice();
		if (mgmDB.selectName(ngood.getName()) == 0)
			return "1";
		return mgmDB.updDB(ngood);
	}

	public String delGood(String name) {
		// Goods good = mgm.delGoodMethid(name);
		// return "��ɾ����Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" +
		// good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�"
		// + good.getPrice();
		if (mgmDB.selectName(name) == 0)
			return "1";
		return mgmDB.delDB(name);
	}

	public String selGood(String name) {
		// Goods good = mgm.selGoodMethod(name);
		// return "�Ѳ鵽��Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" +
		// good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�"
		// + good.getPrice();
		if (mgmDB.selectName(name) == 0)
			return "1";
		return mgmDB.selDB(name);
	}

}
