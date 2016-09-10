package com.one.action_user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.dao.UserDao;
import com.one.vo.User;

@WebServlet("/SearchUserAction")
public class SearchUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		String uunm = (String) session.getAttribute("uunm");
		String ustate = (String) session.getAttribute("ustate");
		if ((int)session.getAttribute("state_u") == 0) {
			uname = request.getParameter("uname");
			uunm = request.getParameter("uunm");
			ustate = request.getParameter("ustate");
		}
		session.setAttribute("state_u", 0);
		ArrayList<User> list = new ArrayList<>();
		list = userDao.select(uname, uunm, Integer.parseInt(ustate));
		session.setAttribute("uname", uname);
		session.setAttribute("uunm", uunm);
		session.setAttribute("ustate", ustate);
		session.setAttribute("userList", list);
		session.setAttribute("state_s", 0);
		session.setAttribute("selectPage", 1);
		response.sendRedirect("SelectPageAction");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
