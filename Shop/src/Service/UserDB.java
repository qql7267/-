package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Entity.User;

public class UserDB {
	private final String file = ".//file//UserFile.txt";
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;

	// 获得所有的用户信息
	public ArrayList<User> getAllUser() {
		ArrayList<User> list = new ArrayList<User>();
		String line;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();
			while (line != null) {
				String arr[] = line.split("[|]");
				User user = new User(Integer.parseInt(arr[0]), arr[1], arr[2], Double.parseDouble(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				list.add(user);
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

	// 写入所有的用户信息
	public void setAllUser(ArrayList<User> list) {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (User user : list) {
				bw.write(user.getId() + "|" + user.getUnm() + "|" + user.getPwd() + "|" + user.getMoney() + "|"
						+ user.getlevel() + "|" + user.getAdmin() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 写入一个用户信息
	public void addUser(User user) {
		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(user.getId() + "|" + user.getUnm() + "|" + user.getPwd() + "|" + user.getMoney() + "|"
					+ user.getlevel() + "|" + user.getAdmin() + "\n");
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
			String arr[] = lastStr.split("[|]");
			lastId = Integer.parseInt(arr[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastId;
	}
}
