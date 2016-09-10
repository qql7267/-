package com.one.action_dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.DeptDao;

@WebServlet("/DelDeptAction")
public class DelDeptAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptDao deptDao = new DeptDao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dno = request.getParameter("dno");
		deptDao.delDept(Integer.parseInt(dno));
		response.sendRedirect("GetAllDeptAction");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
