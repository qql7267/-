package Manages;

import Util.Goods;

public class ManageGoods {
	private ManageGoodsMethod mgm = new ManageGoodsMethod();

	public int addGood(Goods ngood) {
		return mgm.addGoodMethod(ngood);
	}

	public String updGood(Goods ngood) {
		Goods good = mgm.updGoodMethod(ngood);
		return "���޸���Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" + good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�"
				+ good.getPrice();
	}

	public String delGood(String name) {
		Goods good = mgm.delGoodMethid(name);
		return "��ɾ����Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" + good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�"
				+ good.getPrice();
	}

	public String selGood(String name) {
		Goods good = mgm.selGoodMethod(name);
		return "�Ѳ鵽��Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" + good.getNum() + "\n��Ʒ������" + good.getSum() + "\n��Ʒ���ۣ�"
				+ good.getPrice();
	}

}
