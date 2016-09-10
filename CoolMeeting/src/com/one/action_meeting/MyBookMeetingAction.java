package com.one.action_meeting;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.dao.MeetingDao;
import com.one.vo.MeetingInfo;
 
@WebServlet("/MyBookMeetingAction")
public class MyBookMeetingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MeetingInfo> meetingList = new ArrayList<>();
		HttpSession session = request.getSession();
		String uno = String.valueOf((int) session.getAttribute("uno"));
		meetingList = meetingDao.getBookMeeting(uno);
		request.setAttribute("meetingList", meetingList);
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
