package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Entity.ShopCart;

public class ShopCartDB {
	private String file = ".//file//ShopCarFile.txt";
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;

	// 获得所有的购物车商品
	public ArrayList<ShopCart> getAllShopCar() {
		ArrayList<ShopCart> list = new ArrayList<ShopCart>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				String arr[] = line.split("[|]");
				ShopCart shopCart = new ShopCart(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
						Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4], Integer.parseInt(arr[5]),
						Double.parseDouble(arr[6]));
				list.add(shopCart);
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

	// 添加所有的购物车商品
	public void setAllShopCar(ArrayList<ShopCart> list) {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (ShopCart shopCart : list) {
				bw.write(shopCart.getId() + "|" + shopCart.getBuyerId() + "|" + shopCart.getSellerId() + "|"
						+ shopCart.getGoodId() + "|" + shopCart.getName() + "|" + shopCart.getNum() + "|"
						+ shopCart.getPrice() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 添加一个购物车商品
	public void addShopCar(ShopCart shopCart) {
		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(shopCart.getId() + "|" + shopCart.getBuyerId() + "|" + shopCart.getSellerId() + "|"
					+ shopCart.getGoodId() + "|" + shopCart.getName() + "|" + shopCart.getNum() + "|"
					+ shopCart.getPrice() + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获得最后一个ID
	public int getLastId() {
		int lastId = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			String lastStr = null;
			while (line != null) {
				lastStr = line;
				line = br.readLine();
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
