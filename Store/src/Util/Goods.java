package Util;

public class Goods {
	private String name;
	private String num;
	private String sum;
	private String price;

	public Goods(String name, String num, String sum, String price) {
		this.name = name;
		this.num = num;
		this.sum = sum;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
