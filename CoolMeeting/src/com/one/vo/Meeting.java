package com.one.vo;

public class Meeting {
	private int mno;
	private String mname;
	private int mnum;
	private String mstart;
	private String mend;
	private String mreserve;
	private String mroom;
	private String mnote;
	private int morgzer;
	private int mstate;
	private String mcancel;


	public Meeting(int mno, String mname, int mnum, String mstart, String mend, String mreserve, String mroom, String mnote,
			int morgzer, int mstate, String mcancel) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mnum = mnum;
		this.mstart = mstart;
		this.mend = mend;
		this.mreserve = mreserve;
		this.mroom = mroom;
		this.mnote = mnote;
		this.morgzer = morgzer;
		this.mstate = mstate;
		this.mcancel = mcancel;
	}

	public Meeting(String mname, String mstart, String mend, String mnote) {
		super();
		this.mname = mname;
		this.mstart = mstart;
		this.mend = mend;
		this.mnote = mnote;
	}

	public Meeting(int mno, String mname, String mstart) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mstart = mstart;
	}

	public Meeting() {
		super();
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getMstart() {
		return mstart;
	}

	public void setMstart(String mstart) {
		this.mstart = mstart;
	}

	public String getMend() {
		return mend;
	}

	public void setMend(String mend) {
		this.mend = mend;
	}

	public String getMreserve() {
		return mreserve;
	}

	public void setMreserve(String mreserve) {
		this.mreserve = mreserve;
	}

	public String getMroom() {
		return mroom;
	}

	public void setMroom(String mroom) {
		this.mroom = mroom;
	}

	public String getMnote() {
		return mnote;
	}

	public void setMnote(String mnote) {
		this.mnote = mnote;
	}

	public int getMorgzer() {
		return morgzer;
	}

	public void setMorgzer(int morgzer) {
		this.morgzer = morgzer;
	}

	public int getMstate() {
		return mstate;
	}

	public void setMstate(int mstate) {
		this.mstate = mstate;
	}

	public String getMcancel() {
		return mcancel;
	}

	public void setMcancel(String mcancel) {
		this.mcancel = mcancel;
	}

}
