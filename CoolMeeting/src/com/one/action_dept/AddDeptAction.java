package com.one.action_dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.DeptDao;

@WebServlet("/AddDeptAction")
public class AddDeptAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptDao deptDao = new DeptDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dname = request.getParameter("dname");
		if (dname == null || dname.equals("")) {
			request.setAttribute("result", "请输入部门名称");
			request.getRequestDispatcher("departments.jsp").forward(request, response);
		}
		int result = deptDao.confirmDept(dname);
		if (result == 1) {
			request.setAttribute("result", "已存在这个部门");
			request.getRequestDispatcher("departments.jsp").forward(request, response);
		} else {
			deptDao.addDept(dname);
			request.setAttribute("result", "添加成功");
			response.sendRedirect("GetAllDeptAction");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
