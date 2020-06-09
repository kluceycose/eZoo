package com.kluceycose.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kluceycose.ezoo.dao.AnimalDAO;
import com.kluceycose.ezoo.dao.DAOUtilities;
import com.kluceycose.ezoo.dao.FeedingScheduleDAO;
import com.kluceycose.ezoo.model.Animal;
import com.kluceycose.ezoo.model.FeedingSchedule;

@WebServlet("/assignSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Use DAO methods to retrieve Animal and FeedingSchedule lists
		ApplicationContext context = new AnnotationConfigApplicationContext(DAOUtilities.class);
		AnimalDAO animalDao = (AnimalDAO)context.getBean(AnimalDAO.class);
		FeedingScheduleDAO scheduleDao = (FeedingScheduleDAO)context.getBean(FeedingScheduleDAO.class);
		
		List<Animal> animalList = animalDao.getAllAnimals();
		List<FeedingSchedule> scheduleList = scheduleDao.getAllFeedingSchedules();
		
		//Close the context
		((AbstractApplicationContext) context).close();
		
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
		ApplicationContext context = new AnnotationConfigApplicationContext(DAOUtilities.class);
		FeedingScheduleDAO dao = (FeedingScheduleDAO)context.getBean(FeedingScheduleDAO.class);
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
		finally {
			((AbstractApplicationContext) context).close();
		}
	}
}
