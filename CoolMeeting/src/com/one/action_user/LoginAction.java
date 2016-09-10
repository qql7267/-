package com.one.action_user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.dao.UserDao;
import com.one.vo.User;

@WebServlet("/loginActoin")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		String uunm = request.getParameter("uunm");
		String upwd = request.getParameter("upwd");
		User user = userDao.login(uunm, upwd);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("uname", user.getUname());
			session.setAttribute("uno", user.getUno());
			session.setAttribute("state_u", 0);
			response.sendRedirect("NotificationsAction");
		} else {
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
