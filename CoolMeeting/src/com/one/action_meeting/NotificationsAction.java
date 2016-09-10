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
 
@WebServlet("/NotificationsAction")
public class NotificationsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<MeetingInfo> nearList =new ArrayList<>();
		ArrayList<MeetingInfo> cancelList = new ArrayList<>();
		String uno = String.valueOf((int) session.getAttribute("uno"));
		nearList = meetingDao.getNearMeeting(uno);
		cancelList = meetingDao.getCancelMeeting(uno);
		request.setAttribute("nearList", nearList);
		request.setAttribute("cancelList", cancelList);
		request.getRequestDispatcher("notifications.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
