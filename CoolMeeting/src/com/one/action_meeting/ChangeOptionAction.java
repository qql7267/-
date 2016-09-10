package com.one.action_meeting;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.DeptDao;
import com.one.dao.RoomDao;
import com.one.dao.UserDao;
import com.one.vo.Dept;
import com.one.vo.Room;
import com.one.vo.User;
 
@WebServlet("/ChangeOptionAction")
public class ChangeOptionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDao roomDao = new RoomDao();
	private DeptDao deptDao = new DeptDao();
	private UserDao userDao = new UserDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Room> roomList = new ArrayList<>();
		ArrayList<Dept> deptList = new ArrayList<>();
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<User> userSelList = new ArrayList<>();
		String mname = request.getParameter("mname");
		String mnum = request.getParameter("mnum");
		String mstart_date = request.getParameter("mstart_date");
		String mstart_time = request.getParameter("mstart_time");
		String mend_date = request.getParameter("mend_date");
		String mend_time = request.getParameter("mend_time");
		String mnote = request.getParameter("mnote");
		String mroom = request.getParameter("mroom");
		String dno = request.getParameter("dno");
		roomList = roomDao.selectAllRoom();
		deptList = deptDao.getAllDept();
		userList = userDao.getUserDept(Integer.parseInt(dno));
		int selectedLength = Integer.parseInt(request.getParameter("selectedLength"));
		for (int i = 0; i < selectedLength; i++) {
			User user = new User(Integer.parseInt(request.getParameter("uno"+i)), request.getParameter("uname"+i));
			userSelList.add(user);
		}
		request.setAttribute("roomList", roomList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("userList", userList);
		request.setAttribute("userSelList", userSelList);
		request.setAttribute("mname", mname);
		request.setAttribute("mnum", mnum);
		request.setAttribute("mstart_date", mstart_date);
		request.setAttribute("mstart_time", mstart_time);
		request.setAttribute("mend_date", mend_date);
		request.setAttribute("mend_time", mend_time);
		request.setAttribute("mnote", mnote);
		request.setAttribute("mroom", mroom);
		request.setAttribute("dno", dno);
		request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
