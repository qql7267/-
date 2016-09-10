package com.one.action_meeting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.MeetingDao;
import com.one.vo.Meeting;
 
@WebServlet("/CancelMeetingAction")
public class CancelMeetingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Meeting meeting = new Meeting();
		String mno = request.getParameter("mno");
		meeting = meetingDao.getInfoCancel(mno);
		request.setAttribute("mno", mno);
		request.setAttribute("mname", meeting.getMname());
		request.setAttribute("mstart", meeting.getMstart());
		request.getRequestDispatcher("cancelmeeting.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
