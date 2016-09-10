package com.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.one.util.ConnectionDB;
import com.one.vo.Meeting;
import com.one.vo.MeetingInfo;

public class MeetingDao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ConnectionDB connDB = new ConnectionDB();

	public String addMeeting(String mname, String mnum, String mstart, String mend, String mreserve, String mroom,
			String mnote, String morgzer) {
		String mno = null;
		try {
			conn = connDB.ConnectDB();
			String sqlIns = "insert into cm_meeting values(cm_meeting_no_seq.nextval, ?, ?, "
					+ "to_date(?, 'YYYY-MM-DD HH24-MI-SS'), to_date(?, 'YYYY-MM-DD HH24-MI-SS'), to_date(?, 'YYYY-MM-DD HH24-MI-SS'), ?, ?, ?, 1, NULL)";
			psmt = conn.prepareStatement(sqlIns);
			psmt.setString(1, mname);
			psmt.setString(2, mnum);
			psmt.setString(3, mstart);
			psmt.setString(4, mend);
			psmt.setString(5, mreserve);
			psmt.setString(6, mroom);
			psmt.setString(7, mnote);
			psmt.setString(8, morgzer);
			psmt.executeUpdate();
			String sqlSel = "select * from cm_meeting where mreserve = to_date(?,'YYYY-MM-DD HH24-MI-SS')";
			psmt = conn.prepareStatement(sqlSel);
			psmt.setString(1, mreserve);
			rs = psmt.executeQuery();
			rs.next();
			mno = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mno;
	}

	public ArrayList<MeetingInfo> getMeeting(String mname, String mroom, String morgzer, String mreserve_l,
			String mreserve_r, String mdate_l, String mdate_r) {
		ArrayList<MeetingInfo> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select m.mno, m.mname, r.rname, m.mstart, m.mend, m.mreserve, u.uname "
					+ "from cm_meeting m, cm_user u, cm_room r where m.mroom = r.rno and m.morgzer = u.uno";
			if (!mname.equals(""))
				sql += " and m.mname = ?";
			if (!mroom.equals(""))
				sql += " and r.rname = ?";
			if (!morgzer.equals(""))
				sql += " and u.uname = ?";
			if (!mdate_l.equals(""))
				sql += " and m.mstart > to_date(?,'yyyy-mm-dd')";
			if (!mdate_r.equals(""))
				sql += " and m.mend < to_date(?,'yyyy-mm-dd')";
			if (!mreserve_l.equals(""))
				sql += " and m.mreserve > to_date(?,'yyyy-mm-dd')";
			if (!mreserve_r.equals(""))
				sql += " and m.mreserve < to_date(?,'yyyy-mm-dd')";
			psmt = conn.prepareStatement(sql);
			int i = 1;
			if (!mname.equals("")) {
				psmt.setString(i, mname);
				i++;
			}
			if (!mroom.equals("")) {
				psmt.setString(i, mroom);
				i++;
			}
			if (!morgzer.equals("")) {
				psmt.setString(i, morgzer);
				i++;
			}
			if (!mdate_l.equals("")) {
				psmt.setString(i, mdate_l);
				i++;
			}
			if (!mdate_r.equals("")) {
				psmt.setString(i, mdate_r);
				i++;
			}
			if (!mreserve_l.equals("")) {
				psmt.setString(i, mreserve_l);
				i++;
			}
			if (!mreserve_r.equals("")) {
				psmt.setString(i, mreserve_r);
				i++;
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(meetingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getMeetingNum(String mno) {
		int result = 0;
		try {
			conn = connDB.ConnectDB();
			String sql = "select count(jmno) from cm_join where jmno = " + mno;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Meeting getMeetingNote(String mno) {
		Meeting meeting = null;
		try {
			conn = connDB.ConnectDB();
			String sql = "select mname, mstart, mend, mnote from cm_meeting where mno = " + mno;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			meeting = new Meeting(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return meeting;
	}

	public ArrayList<MeetingInfo> getJoinMeeting(String uno) {
		ArrayList<MeetingInfo> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select m.mno, m.mname, r.rname, m.mstart, m.mend, m.mreserve, u.uname from cm_meeting m, cm_user u, cm_room r, cm_join j "
					+ "where m.morgzer = u.uno and m.mroom = r.rno and m.mno = j.jmno and j.jparter = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(meetingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<MeetingInfo> getBookMeeting(String uno) {
		ArrayList<MeetingInfo> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select m.mno, m.mname, r.rname, m.mstart, m.mend, m.mreserve from cm_meeting m, cm_room r "
					+ "where m.mroom = r.rno and m.morgzer = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(meetingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Meeting getInfoCancel(String mno) {
		Meeting meeting = null;
		try {
			conn = connDB.ConnectDB();
			String sql = "select mno, mname, mstart from cm_meeting where mno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mno);
			rs = psmt.executeQuery();
			rs.next();
			meeting = new Meeting(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return meeting;
	}

	public void cancelMeeting(String mno, String mcancel) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_meeting set mstate = 0, mcancel = ? where mno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mcancel);
			psmt.setString(2, mno);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<MeetingInfo> getNearMeeting(String uno){
		ArrayList<MeetingInfo> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select m.mno, m.mname, r.rname, m.mstart, m.mend from cm_meeting m, cm_room r, cm_join j "
					+ "where m.mstart - sysdate < 7 and m.mstart - sysdate >= 0 and m.mstate != 0"
					+ "and m.mroom = r.rno and j.jmno = m.mno and j.jparter = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				list.add(meetingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<MeetingInfo> getCancelMeeting(String uno){
		ArrayList<MeetingInfo> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select m.mno, m.mname, r.rname, m.mstart, m.mend, m.mcancel from cm_meeting m, cm_room r, cm_join j "
					+ "where m.mstart - sysdate < 7 and m.mstart - sysdate >= 0 and m.mstate = 0"
					+ "and m.mroom = r.rno and j.jmno = m.mno and j.jparter = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo(rs.getString(2), rs.getInt(1), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(meetingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
