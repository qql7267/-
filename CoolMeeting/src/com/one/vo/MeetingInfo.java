package com.one.vo;

public class MeetingInfo {
	private int mno;
	private String mname;
	private String mroom;
	private String mstart;
	private String mend;
	private String mreserve;
	private String morgzer;
	private String mcancel;

	public MeetingInfo(int mno, String mname, String mroom, String mstart, String mend, String mreserve,
			String morgzer) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mroom = mroom;
		this.mstart = mstart;
		this.mend = mend;
		this.mreserve = mreserve;
		this.morgzer = morgzer;
	}

	public MeetingInfo(int mno, String mname, String mroom, String mstart, String mend, String mreserve) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mroom = mroom;
		this.mstart = mstart;
		this.mend = mend;
		this.mreserve = mreserve;
	}

	public MeetingInfo(int mno, String mname, String mroom, String mstart, String mend) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mroom = mroom;
		this.mstart = mstart;
		this.mend = mend;
	}
	
	

	public MeetingInfo(String mname, int mno, String mroom, String mstart, String mend, String mcancel) {
		super();
		this.mname = mname;
		this.mno = mno;
		this.mroom = mroom;
		this.mstart = mstart;
		this.mend = mend;
		this.mcancel = mcancel;
	}

	public MeetingInfo() {
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

	public String getMroom() {
		return mroom;
	}

	public void setMroom(String mroom) {
		this.mroom = mroom;
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

	public String getMorgzer() {
		return morgzer;
	}

	public void setMorgzer(String morgzer) {
		this.morgzer = morgzer;
	}

	public String getmcancel() {
		return mcancel;
	}

	public void setmcancel(String mcancel) {
		this.mcancel = mcancel;
	}

}
