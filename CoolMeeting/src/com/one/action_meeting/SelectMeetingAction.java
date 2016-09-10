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
 
@WebServlet("/SelectMeetingAction")
public class SelectMeetingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<MeetingInfo> meetingInfoList = new ArrayList<>();
		String mname = request.getParameter("mname");
		String mroom = request.getParameter("mroom");
		String morgzer = request.getParameter("morgzer");
		String mreserve_l = request.getParameter("mreserve_l");
		String mreserve_r = request.getParameter("mreserve_r");
		String mdate_l = request.getParameter("mdate_l");
		String mdate_r = request.getParameter("mdate_r");
		meetingInfoList = meetingDao.getMeeting(mname, mroom, morgzer, mreserve_l, mreserve_r, mdate_l, mdate_r);
		session.setAttribute("meetingInfoList", meetingInfoList);
		response.sendRedirect("SelectMeetingPageAction");
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
