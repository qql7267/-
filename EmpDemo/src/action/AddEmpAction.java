package action;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import vo.Dept;
import vo.Emp;

public class AddEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		//get请求提交数据的中文处理方案
		//ename = new String(ename.getBytes(),"UTF-8");
		
		String job = request.getParameter("job");
		int mgr = Integer.parseInt(request.getParameter("mgr"));
		String date = request.getParameter("hireDate");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDate=null;
		try {
			java.util.Date strDate = simpleDateFormat.parse(date);
			
			 sqlDate = new Date(strDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float sal = Float.parseFloat(request.getParameter("sal"));
		String comm = request.getParameter("comm");
		Float commFloat = null;
		if(!comm.equals("")){
			commFloat = Float.parseFloat(comm);
		}
		int deptno = Integer.parseInt(request.getParameter("deptName"));
		Dept dept = new Dept();
		dept.setDeptno(deptno);
		Emp emp = new Emp(Integer.parseInt(empno),ename,job,mgr,sqlDate,sal,commFloat,dept);
		try {
			new EmpDAO().addEmp(emp);
			
			response.sendRedirect("getAllAction");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		doGet(request, response);
	}

}

