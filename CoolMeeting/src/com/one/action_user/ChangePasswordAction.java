package com.one.action_user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.dao.UserDao;

@WebServlet("/ChangePasswordAction")
public class ChangePasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int uno = (int) session.getAttribute("uno");
		String uoldPwd = request.getParameter("uoldPwd");
		String unewPwd = request.getParameter("unewPwd");
		String unewPwd2 = request.getParameter("unewPwd2");
		int result = userDao.confirmPwd(uno, uoldPwd);
		if (result == 0) {
			request.setAttribute("result", "密码输入错误");
			request.getRequestDispatcher("changepassword.jsp").forward(request, response);
//			return;
		}
		if (!unewPwd.equals(unewPwd2)) {
			request.setAttribute("result", "两次密码输入不同");
			request.getRequestDispatcher("changepassword.jsp").forward(request, response);
//			return;
		}
		if(result == 1) {
		userDao.changePwd(uno, unewPwd);
		request.setAttribute("result", "修改成功");
		request.getRequestDispatcher("changepassword.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
