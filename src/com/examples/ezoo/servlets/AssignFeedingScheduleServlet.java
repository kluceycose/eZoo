package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/assignSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Use DAO methods to retrieve Animal and FeedingSchedule lists
		AnimalDAO animalDao = DAOUtilities.getAnimalDao();
		FeedingScheduleDAO scheduleDao = DAOUtilities.getFeedingScheduleDao();
		
		List<Animal> animalList = animalDao.getAllAnimals();
		List<FeedingSchedule> scheduleList = scheduleDao.getAllFeedingSchedules();
		
		//Pass the lists to session variables
		request.getSession().setAttribute("animals", animalList);
		request.getSession().setAttribute("schedules", scheduleList);
		request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameters
		long animalId = Long.parseLong(request.getParameter("animalId"));
		long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.updateAnimalFeedingSchedule(animalId, scheduleId);
			request.getSession().setAttribute("message", "Schedule successfully added to Animal");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		}
		catch(Exception e) {
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem adding the schedule to the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);
		}
	}
}
