package Entity;

public class ShopCart {
	// 交易编号
	private int id;
	// 买家编号
	private int buyerId;
	// 卖家编号
	private int sellerId;
	// 商品编号
	private int goodId;
	// 商品名称
	private String name;
	// 商品数量
	private int num;
	// 商品单价
	private double price;

	public ShopCart(int id, int buyerId, int sellerId, int goodId, String name, int num, double price) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.goodId = goodId;
		this.name = name;
		this.num = num;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double totalPrice() {
		return this.num * price;
	}
}
