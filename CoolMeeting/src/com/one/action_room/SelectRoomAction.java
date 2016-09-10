package com.one.action_room;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.RoomDao;
import com.one.vo.Room;
 
@WebServlet("/SelectRoomAction")
public class SelectRoomAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDao roomDao = new RoomDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Room> list = new ArrayList<>();
		list = roomDao.selectAllRoom();
		request.setAttribute("roomList", list);
		request.getRequestDispatcher("meetingrooms.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
