package Entity;

public class Goods {
	// 商品编号
	private int id;
	// 商品名称
	private String name;
	// 商品库存
	private int stock;
	// 商品单价
	private double price;
	// 商品类别
	private String category;
	// 商品归属者编号
	private int ownerId;

	public Goods() {

	}

	public Goods(int id, String name, int stock, double price, String category, int ownerId) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.category = category;
		this.ownerId = ownerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
