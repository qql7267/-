package com.one.vo;

public class Join {
	private int jno;
	private String jorgzer;
	private String jparter;
	private int jmno;

	public Join(int jno, String jorgzer, String jparter, int jmno) {
		super();
		this.jno = jno;
		this.jorgzer = jorgzer;
		this.jparter = jparter;
		this.jmno = jmno;
	}

	public Join() {
		super();
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getJorgzer() {
		return jorgzer;
	}

	public void setJorgzer(String jorgzer) {
		this.jorgzer = jorgzer;
	}

	public String getJparter() {
		return jparter;
	}

	public void setJparter(String jparter) {
		this.jparter = jparter;
	}

	public int getJmno() {
		return jmno;
	}

	public void setJmno(int jmno) {
		this.jmno = jmno;
	}

}
