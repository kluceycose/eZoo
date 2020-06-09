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
import com.kluceycose.ezoo.model.Animal;


/**
 * Servlet implementation class AnimalCareServlet
 */
@WebServlet(description = "This servlet is the main interface into the Animal Care System", urlPatterns = { "/animalCare" })
public class AnimalCareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Animals from the Database
		ApplicationContext context = new AnnotationConfigApplicationContext(DAOUtilities.class);
		AnimalDAO dao = (AnimalDAO)context.getBean(AnimalDAO.class);
		List<Animal> animals = dao.getAllAnimals();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("animals", animals);
		
		// Combined the search for the largest and longest animals to improve execution time - kluceycose
		Animal largest = new Animal();
		Animal longest = new Animal();
		for (Animal a : animals) {
			if (a.getWeight() > largest.getWeight())
				largest = a;
			if (a.getName().length() > longest.getName().length())
				longest = a;
		}
		request.getSession().setAttribute("largestAnimal", largest);
	
		request.getSession().setAttribute("longestNamedAnimal", longest);
		
		((AbstractApplicationContext) context).close();
		
		request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
	}


}
