package com.one.action_user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.DeptDao;
import com.one.dao.UserDao;
import com.one.vo.Dept;

@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private DeptDao deptDao = new DeptDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String uunm = request.getParameter("uunm");
		String upwd = request.getParameter("upwd");
		String upwd2 = request.getParameter("upwd2");
		String uphone = request.getParameter("uphone");
		String umail = request.getParameter("umail");
		String udeptid = request.getParameter("udeptid");
		int result_uunm = userDao.confirmUnm(uunm);
		ArrayList<Dept> list = new ArrayList<>();
		list = deptDao.getAllDept();
		request.setAttribute("deptList", list);
		if (result_uunm == 1) {
			request.setAttribute("result", "已存在该用户名");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		if (upwd.length() < 6) {
			request.setAttribute("result", "密码长度不够");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		if (!upwd.equals(upwd2)) {
			request.setAttribute("result", "两次密码输入不同");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		if(result_uunm == 0 && upwd.length() >= 6 && upwd.equals(upwd2)){
			userDao.register(uname, uunm, upwd, uphone, umail, udeptid);
			request.setAttribute("result", "注册成功，请等待审核");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
