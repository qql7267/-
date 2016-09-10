package com.one.action_user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.vo.User;

@WebServlet("/SelectPageAction")
public class SelectPageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int selectPage = (int) session.getAttribute("selectPage");
		int state = (int) session.getAttribute("state_s");
		int jump = 1;
		if (state == 1)
			jump = Integer.parseInt(request.getParameter("jump"));
		session.setAttribute("state_s", 1);
		ArrayList<User> list = new ArrayList<>();
		ArrayList<User> list_show = new ArrayList<>();
		list = (ArrayList<User>) session.getAttribute("userList");
		int listSize = list.size();
		int listPage = listSize / 10 + 1;
		if (listSize % 10 == 0)
			listPage--;
		if (jump == 1)
			selectPage = 1;
		if (jump == 2)
			selectPage = listPage;
		if (jump == 3)
			selectPage += 1;
		if (jump == 4)
			selectPage -= 1;
		if (jump == 5)
			selectPage = Integer.parseInt(request.getParameter("selectPage"));
		if (selectPage >= listPage)
			for (int i = selectPage * 10 - 10; i < listSize; i++)
				list_show.add(list.get(i));
		else
			for (int i = selectPage * 10 - 10; i < selectPage * 10; i++)
				list_show.add(list.get(i));
		session.setAttribute("selectPage", selectPage);
		session.setAttribute("listSize", listSize);
		session.setAttribute("listPage", listPage);
		request.setAttribute("showList", list_show);
		request.getRequestDispatcher("searchemployees.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
