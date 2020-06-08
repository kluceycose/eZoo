package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.HashMap;
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

/**
 * Servlet implementation class FeedingScheduleServlet
 */
@WebServlet("/feedingSchedules")
public class FeedingScheduleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Get the list of Feeding Schedules and the list of Animals from the database
		AnimalDAO animalDao = DAOUtilities.getAnimalDao();
		List<Animal> animals = animalDao.getAllAnimals();
		
		FeedingScheduleDAO scheduleDao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> schedules = scheduleDao.getAllFeedingSchedules();
		
		//Map animals to feeding schedules
		HashMap<FeedingSchedule, String> scheduledAnimals = new HashMap<>();
		for(FeedingSchedule schedule: schedules) {
			StringBuilder animalList = new StringBuilder("");
			for(Animal animal: animals) {
				if(animal.getFeedingScheduleId() == schedule.getScheduleId()) {
					if(animalList.length() > 0) {
						animalList.append(", ");
					}
					animalList.append(animal.getName());
				}
			}
			scheduledAnimals.put(schedule, animalList.toString());
		}
		
		//Populate the lists into variables stored in the session
		request.getSession().setAttribute("scheduledAnimals", scheduledAnimals);
		request.getSession().setAttribute("schedules", schedules);
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
	}

}
