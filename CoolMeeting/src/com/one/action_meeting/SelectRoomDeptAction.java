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

@WebServlet("/SelectRoomDeptAction")
public class SelectRoomDeptAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDao roomDao = new RoomDao();
	private DeptDao deptDao = new DeptDao();
	private UserDao userDao = new UserDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Room> roomList = new ArrayList<>();
		ArrayList<Dept> deptList = new ArrayList<>();
		ArrayList<User> userList = new ArrayList<>();
		roomList = roomDao.selectAllRoom();
		deptList = deptDao.getAllDept();
		userList = userDao.getUserDept(deptList.get(0).getDno());
		request.setAttribute("roomList", roomList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("userList", userList);
		request.setAttribute("dno", deptList.get(0).getDno());
		request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
