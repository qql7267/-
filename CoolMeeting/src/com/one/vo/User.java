package com.one.vo;

public class User {
	private int uno;
	private String uname;
	private String uunm;
	private String upwd;
	private String uphone;
	private String umail;
	private int ustate;
	private String udpt;

	public User(int uno, String uname, String uunm, String upwd, String uphone, String umail, int ustate, String udpt) {
		super();
		this.uno = uno;
		this.uname = uname;
		this.uunm = uunm;
		this.upwd = upwd;
		this.uphone = uphone;
		this.umail = umail;
		this.ustate = ustate;
		this.udpt = udpt;
	}

	public User(int uno, String uname) {
		super();
		this.uno = uno;
		this.uname = uname;
	}

	public User(String uname, String uunm, String uphone, String umail) {
		super();
		this.uname = uname;
		this.uunm = uunm;
		this.uphone = uphone;
		this.umail = umail;
	}

	public User(int uno, String uname, String uunm, String uphone, String umail, int ustate) {
		super();
		this.uno = uno;
		this.uname = uname;
		this.uunm = uunm;
		this.uphone = uphone;
		this.umail = umail;
		this.ustate = ustate;
	}

	public User(String uname, String uphone, String umail) {
		super();
		this.uname = uname;
		this.uphone = uphone;
		this.umail = umail;
	}

	public User() {
		super();
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getuunm() {
		return uunm;
	}

	public void setuunm(String uunm) {
		this.uunm = uunm;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getuphone() {
		return uphone;
	}

	public void setuphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public String getUdpt() {
		return udpt;
	}

	public void setUdpt(String udpt) {
		this.udpt = udpt;
	}

}
