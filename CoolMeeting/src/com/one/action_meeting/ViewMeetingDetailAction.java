package com.one.action_meeting;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.dao.JoinDao;
import com.one.dao.MeetingDao;
import com.one.vo.Meeting;
import com.one.vo.User;
 
@WebServlet("/ViewMeetingDetailAction")
public class ViewMeetingDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
	private JoinDao joinDao = new JoinDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> joinList = new ArrayList<>();
		String mno = request.getParameter("mno");
		int mnum = meetingDao.getMeetingNum(mno);
		Meeting meeting = meetingDao.getMeetingNote(mno);
		joinList = joinDao.getJoin(mno);
		request.setAttribute("mname", meeting.getMname());
		request.setAttribute("mnum", mnum);
		request.setAttribute("mstart", meeting.getMstart());
		request.setAttribute("mend", meeting.getMend());
		request.setAttribute("mnote", meeting.getMnote());
		request.setAttribute("joinList", joinList);
		request.getRequestDispatcher("meetingdetails.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}