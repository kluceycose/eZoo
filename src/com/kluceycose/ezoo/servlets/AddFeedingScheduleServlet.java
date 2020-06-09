package com.kluceycose.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kluceycose.ezoo.dao.DAOUtilities;
import com.kluceycose.ezoo.dao.FeedingScheduleDAO;
import com.kluceycose.ezoo.model.FeedingSchedule;

@WebServlet("/addFeedingSchedule")
public class AddFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		boolean update = Boolean.parseBoolean(request.getParameter("update").trim());
		System.out.println(update);
		long id = Long.parseLong(request.getParameter("id"));
		String time = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		String name = request.getParameter("name");
		
		//Create FeedingSchedule object
		FeedingSchedule scheduleToSave = new FeedingSchedule(id, time, recurrence, food, notes, name);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			if(update) {
				dao.saveFeedingSchedule(scheduleToSave);
				request.getSession().setAttribute("message", "Schedule saved successfully");
				request.getSession().setAttribute("messageClass", "alert-success");
			} else {
				dao.addFeedingSchedule(scheduleToSave);
				request.getSession().setAttribute("message", "Schedule created successfully");
				request.getSession().setAttribute("messageClass", "alert-success");
			}
			request.getSession().setAttribute("update", "false");
			response.sendRedirect("feedingSchedules");
		}
		catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + scheduleToSave.getScheduleId() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
		}
	}
}
