package com.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.one.util.ConnectionDB;
import com.one.vo.Room;

public class RoomDao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ConnectionDB connDB = new ConnectionDB();

	public void addRoom(String rroom, String rname, String rvol, String rstate, String rnote) {
		try {
			conn = connDB.ConnectDB();
			String sql = "insert into cm_room values(cm_room_no_seq.nextval, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rroom);
			psmt.setString(2, rname);
			psmt.setString(3, rvol);
			psmt.setString(4, rstate);
			psmt.setString(5, rnote);
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

	public ArrayList<Room> selectAllRoom() {
		ArrayList<Room> list = new ArrayList<>();
		try {
			conn = connDB.ConnectDB();
			String sql = "select rno, rroom, rname, rvol, rstate from cm_room";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Room room = new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				list.add(room);
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

	public Room selectRoom(String rno) {
		Room room = null;
		try {
			conn = connDB.ConnectDB();
			String sql = "select * from cm_room where rno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rno);
			rs = psmt.executeQuery();
			rs.next();
			room = new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6));
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
		return room;
	}
	public void UpdateRoom(String rno, String rroom, String rname, String rvol, String rstate, String rnote) {
		try {
			conn = connDB.ConnectDB();
			String sql = "update cm_room set rroom = ?, rname = ?, rvol = ?, rstate = ?, rnote = ? where rno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rroom);
			psmt.setString(2, rname);
			psmt.setString(3, rvol);
			psmt.setString(4, rstate);
			psmt.setString(5, rnote);
			psmt.setString(6, rno);
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
}
