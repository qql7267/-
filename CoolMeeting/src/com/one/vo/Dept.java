package com.one.vo;

public class Dept {
	private int dno;
	private String dname;

	public Dept(int dno, String dname) {
		super();
		this.dno = dno;
		this.dname = dname;
	}

	public Dept() {
		super();
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

}
