package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOStream {
	private final String file = "GoodsList.txt";
	private FileReader fr = null;
	private FileWriter fw = null;
	private BufferedReader br = null;
	private BufferedWriter bw = null;

	public void addGoods(ArrayList<Goods> goodList) {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (Goods good : goodList) {
				bw.write(good.getName() + "|" + good.getNum() + "|"
						+ good.getSum() + "|" + good.getPrice());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("´íÎó£º" + e.getMessage());
		}
	}

	public ArrayList<Goods> getGoods() {
		ArrayList<Goods> list = new ArrayList<Goods>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				String arr[] = line.split("[|]");
				Goods good = new Goods(arr[0], arr[1], arr[2], arr[3]);
				list.add(good);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("´íÎó£º" + e.getMessage());
		} catch (IOException e) {
			System.out.println("´íÎó£º" + e.getMessage());
		}
		return list;
	}
}
