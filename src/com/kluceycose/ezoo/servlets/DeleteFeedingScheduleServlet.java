package com.kluceycose.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kluceycose.ezoo.dao.DAOUtilities;
import com.kluceycose.ezoo.dao.FeedingScheduleDAO;

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Get parameters
		long scheduleIdToDelete = Long.parseLong(request.getParameter("scheduleId"));
		
		//Call DAO method
		ApplicationContext context = new AnnotationConfigApplicationContext(DAOUtilities.class);
		FeedingScheduleDAO dao = (FeedingScheduleDAO)context.getBean(FeedingScheduleDAO.class);
		try {
			dao.deleteFeedingSchedule(scheduleIdToDelete);
			request.getSession().setAttribute("message", "Schedule delete successful");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
		}
		catch(Exception e) {
			e.printStackTrace();
			
			//Change message
			request.getSession().setAttribute("message", "There was a problem deleting the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("feedingSchedues.jsp").forward(request, response);
		}
		finally {
			((AbstractApplicationContext) context).close();
		}
		
	}
}
