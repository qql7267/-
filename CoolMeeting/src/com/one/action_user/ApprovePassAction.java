package com.one.action_user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.UserDao;
import com.one.vo.User;

@WebServlet("/ApprovePassAction")
public class ApprovePassAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uunm = request.getParameter("uunm");
		userDao.pass(uunm);
		ArrayList<User> list = new ArrayList<>();
		list = userDao.getApprove();
		request.setAttribute("approveList", list);
		request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
