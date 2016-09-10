package com.one.action_meeting;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.one.dao.JoinDao;
import com.one.dao.MeetingDao;
import com.one.vo.User;
 
@WebServlet("/AddMeetingAction")
public class AddMeetingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingDao meetingDao = new MeetingDao();
	private JoinDao joinDao = new JoinDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> joinList = new ArrayList<>();
		HttpSession session = request.getSession();
		String mname = request.getParameter("mname");
		String mnum = request.getParameter("mnum");
		String mstart_date = request.getParameter("mstart_date");
		String mstart_time = request.getParameter("mstart_time");
		String mend_date = request.getParameter("mend_date");
		String mened_time = request.getParameter("mend_time");
		String mroom = request.getParameter("mroom");
		String mnote = request.getParameter("mnote");
		String morgzer = String.valueOf((int)session.getAttribute("uno"));
		String mstart = mstart_date +" "+ mstart_time;
		String mend = mend_date +" "+ mened_time;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mreserve =df.format(new Date());
		String mno = meetingDao.addMeeting(mname, mnum, mstart, mend, mreserve, mroom, mnote, morgzer);
		int selectedLength = Integer.parseInt(request.getParameter("selectedLength"));
		for (int i = 0; i < selectedLength; i++) 
			joinDao.addJoin(request.getParameter("uno"+i), mno);
		joinList = joinDao.getJoin(mno);
		request.setAttribute("mname", mname);
		request.setAttribute("mnum", meetingDao.getMeetingNum(mno));
		request.setAttribute("mstart", mstart);
		request.setAttribute("mend", mend);
		request.setAttribute("mno", mno);
		request.setAttribute("mnote", mnote);
		request.setAttribute("joinList", joinList);
		request.getRequestDispatcher("meetingdetails.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
