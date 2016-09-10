package com.one.vo;

public class Room {
	private int rno;
	private String rroom;
	private String rname;
	private int rvol;
	private int rstate;
	private String rnote;

	public Room(int rno, String rroom, String rname, int rvol, int rstate, String rnote) {
		super();
		this.rno = rno;
		this.rroom = rroom;
		this.rname = rname;
		this.rvol = rvol;
		this.rstate = rstate;
		this.rnote = rnote;
	}

	public Room(int rno, String rroom, String rname, int rvol, int rstate) {
		super();
		this.rno = rno;
		this.rroom = rroom;
		this.rname = rname;
		this.rvol = rvol;
		this.rstate = rstate;
	}

	public Room() {
		super();
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getRroom() {
		return rroom;
	}

	public void setRroom(String rroom) {
		this.rroom = rroom;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getRvol() {
		return rvol;
	}

	public void setRvol(int rvol) {
		this.rvol = rvol;
	}

	public int getRstate() {
		return rstate;
	}

	public void setRstate(int rstate) {
		this.rstate = rstate;
	}

	public String getRnote() {
		return rnote;
	}

	public void setRnote(String rnote) {
		this.rnote = rnote;
	}

}
