package Manages;

import java.util.ArrayList;

import Util.Goods;
import Util.IOStream;

public class ManageGoodsMethod {
	private IOStream stream = new IOStream();

	public int addGoodMethod(Goods ngood) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = stream.getGoods();
		for (Goods good : list) {
			if (good.getName().equals(ngood.getName()))
				return 1;
			if (good.getNum().equals(ngood.getNum()))
				return 2;
		}
		list.add(ngood);
		stream.addGoods(list);
		return 0;
	}

	public Goods updGoodMethod(Goods good) {
		ArrayList<Goods> list = new ArrayList<Goods>();
		list = stream.getGoods();
		int i;
		for (i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(good.getName())) {
				if (!good.getNum().equals(""))
					list.get(i).setNum(good.getNum());
				if (!good.getSum().equals(""))
					list.get(i).setSum(good.getSum());
				if (!good.getPrice().equals(""))
					list.get(i).setPrice(good.getPrice());
				break;
			}
		}
		stream.addGoods(list);
		return list.get(i);
	}

	public Goods delGoodMethid(String name) {
		ArrayList<Goods> list = new ArrayList<>();
		list = stream.getGoods();
		int i;
		for (i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				list.remove(i);
				break;
			}
		}
		stream.addGoods(list);
		return list.get(i);
	}

	public Goods selGoodMethod(String name) {
		Goods good = null;
		for (Goods get_good : stream.getGoods()) {
			if (get_good.getName().equals(name)) {
				return get_good;
			}
		}
		return good;
	}

}
