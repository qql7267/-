package Entity;

public class User {
	// �˻����
	private int id;
	// �˻��û���
	private String unm;
	// �˻�����
	private String pwd;
	// �˻����н�Ǯ
	private double money;
	// �˻��ۿ۵ȼ�
	private int level;
	// �˻�Ȩ�޵ȼ�
	private int admin;

	public User(int id, String unm, String pwd, double money, int level, int admin) {
		this.id = id;
		this.unm = unm;
		this.pwd = pwd;
		this.money = money;
		this.level = level;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnm() {
		return unm;
	}

	public void setUnm(String unm) {
		this.unm = unm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getlevel() {
		return level;
	}

	public void setLv(int level) {
		this.level = level;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

}
