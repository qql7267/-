package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Goods;

public class GoodsDB {
	private String file = ".//file//GoodsFile.txt";
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;

	// 获得所有的商品
	public ArrayList<Goods> getAllGoods() {
		ArrayList<Goods> list = new ArrayList<Goods>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				String arr[] = line.split("[|]");
				Goods goods = new Goods(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]),
						Double.parseDouble(arr[3]), arr[4], Integer.parseInt(arr[5]));
				list.add(goods);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 添加所有的商品
	public void setAllGoods(ArrayList<Goods> list) {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (Goods good : list) {
				bw.write(good.getId() + "|" + good.getName() + "|" + good.getStock() + "|" + good.getPrice() + "|"
						+ good.getCategory() + "|" + good.getOwnerId() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 添加一个商品
	public void addGoods(Goods good) {
		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(good.getId() + "|" + good.getName() + "|" + good.getStock() + "|" + good.getPrice() + "|"
					+ good.getCategory() + "|" + good.getOwnerId() + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获得最后一个ID
	public int getLastId() {
		int lastId = 1;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			String lastStr = null;
			while (line != null) {
				lastStr = line;
				line = br.readLine();
			}
			if (lastStr != null) {
				String arr[] = lastStr.split("[|]");
				lastId = Integer.parseInt(arr[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastId;
	}
}
