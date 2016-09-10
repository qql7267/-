package com.one.action_meeting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.MeetingDao;
 
@WebServlet("/SureCancelAction")
public class SureCancelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno = request.getParameter("mno");
		String mcancel = request.getParameter("mcancel");
		meetingDao.cancelMeeting(mno, mcancel);
		response.sendRedirect("MyBookMeetingAction");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
