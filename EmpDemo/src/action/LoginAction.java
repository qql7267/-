package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import vo.User;

/**
 * Servlet implementation class LoginAction
 */

public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User(request.getParameter("name"),request.getParameter("pass"));
		try {
			user = new UserDAO().checkUser(user);
			if(user == null){
				String errMsg = "”√ªß√˚√‹¬Î¥ÌŒÛ£°";
				request.setAttribute("errMsg", errMsg);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				response.sendRedirect("welcom.jsp");
			}
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
