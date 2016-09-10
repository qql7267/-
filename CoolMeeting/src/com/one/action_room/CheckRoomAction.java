package com.one.action_room;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.RoomDao;
import com.one.vo.Room;
 
@WebServlet("/CheckRoomAction")
public class CheckRoomAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDao roomDao = new RoomDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rno = request.getParameter("rno");
		Room room = roomDao.selectRoom(rno);
		request.setAttribute("rno", room.getRno());
		request.setAttribute("rroom", room.getRroom());
		request.setAttribute("rname", room.getRname());
		request.setAttribute("rvol", room.getRvol());
		request.setAttribute("rstate", room.getRstate());
		request.setAttribute("rnote", room.getRnote());
		request.getRequestDispatcher("roomdetails.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
