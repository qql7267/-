package com.one.action_room;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.RoomDao;

@WebServlet("/AddRoomAction")
public class AddRoomAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDao roomDao = new RoomDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rroom = request.getParameter("rroom");
		String rname = request.getParameter("rname");
		String rvol = request.getParameter("rvol");
		String rstate = request.getParameter("rstate");
		String rnote = request.getParameter("rnote");
		if (rroom == "" || rname == "" || rvol == "" || rstate == "" || rnote == "")
			request.setAttribute("result", "有值为空，请重新输入");
		else {
			roomDao.addRoom(rroom, rname, rvol, rstate, rnote);
			request.setAttribute("result", "添加成功");
		}
		request.getRequestDispatcher("addmeetingroom.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
