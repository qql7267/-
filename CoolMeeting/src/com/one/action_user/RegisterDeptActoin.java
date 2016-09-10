package com.one.action_user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.DeptDao;
import com.one.vo.Dept;

@WebServlet("/RegisterDeptActoin")
public class RegisterDeptActoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptDao deptDao =new DeptDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Dept> list = new ArrayList<>();
		list = deptDao.getAllDept();
		request.setAttribute("deptList", list);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
