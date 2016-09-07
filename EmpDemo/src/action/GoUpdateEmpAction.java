package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAO;
import vo.Dept;
import vo.Emp;

/**
 * Servlet implementation class DeleteEmpAction
 */

public class GoUpdateEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String empno = request.getParameter("empno");
		try {
			List<Dept> list = new EmpDAO().getAllDept();
			List<Emp> listEmp = new EmpDAO().getAll();
			request.setAttribute("listDept", list);
			request.setAttribute("listEmp", listEmp);
			Emp emp = new EmpDAO().getEmpById(Integer.parseInt(empno));
			request.setAttribute("emp", emp);
			request.getRequestDispatcher("updateEmp.jsp").forward(request,response);
		} catch (NumberFormatException | SQLException e) {
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
