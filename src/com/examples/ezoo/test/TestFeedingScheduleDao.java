package com.examples.ezoo.test;

import java.util.List;

import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

//import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFeedingScheduleDao {

	public static void main(String[] args) {
		testAddFeedingSchedule();
		testGetAllFeedingSchedules();
		testGetFeedingSchedule();
		testSaveFeedingSchedule();
		testUpdateAnimalFeedingSchedule();
		testRemoveAnimalFeedingSchedule();
	}
	
	static void testAddFeedingSchedule() {
		System.out.println("testAddFeedingSchedule:");
		FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		FeedingSchedule test = new FeedingSchedule(0L, "test", "test", "test", "test", "test");
		try {
			dao.addFeedingSchedule(test);
			List<FeedingSchedule> list = dao.getAllFeedingSchedules();
			for(FeedingSchedule schedule: list) {
				if(schedule.getScheduleId() == 0L) {
					System.out.println("Add success");
				}
			}
			dao.deleteFeedingSchedule(test.getScheduleId());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		
	}
	
	static void testGetAllFeedingSchedules() {
		System.out.println("testGetAllFeedingSchedules");
		FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		List<FeedingSchedule> list = dao.getAllFeedingSchedules();
		
		for(FeedingSchedule schedule: list) {
			System.out.println(schedule);
		}
		System.out.println("Verify manually with pgAdmin\n");
	}
	
	static void testGetFeedingSchedule() {
		System.out.println("testGetFeedingSchedule");
		FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		Animal animal = new Animal();
		animal.setFeedingScheduleId(1L);
		
		FeedingSchedule schedule = dao.getFeedingSchedule(animal);
		System.out.println(schedule);
		System.out.println();
	}
	
	static void testSaveFeedingSchedule() {
		System.out.println("testSaveFeedingSchedule");
		FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		List<FeedingSchedule> schedules = dao.getAllFeedingSchedules();
		FeedingSchedule test = schedules.get(0);
		String temp = test.getFeedingTime();
		System.out.println(test);
		test.setFeedingTime("test");
		try {
			dao.saveFeedingSchedule(test);
			schedules = dao.getAllFeedingSchedules();
			for(FeedingSchedule schedule: schedules) {
				if(schedule.getScheduleId() == test.getScheduleId()) {
					System.out.println(schedule);
				}
			}
			test.setFeedingTime(temp);
			dao.saveFeedingSchedule(test);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	static void testUpdateAnimalFeedingSchedule() {
		System.out.println("testUpdateAnimalFeedingSchedule:");
		
		FeedingScheduleDAOImpl dao = new FeedingScheduleDAOImpl();
		AnimalDaoImpl animalDao = new AnimalDaoImpl();
		Animal animal = animalDao.getAllAnimals().get(0);
		FeedingSchedule temp = animal.getFeedingSchedule();
		FeedingSchedule test = new FeedingSchedule(0L, "test", "test", "test", "test", "test");
		System.out.println(animal);
		animal.setFeedingSchedule(test);

		try {
			// Add test schedule to database and update animal with it
			dao.addFeedingSchedule(test);
			dao.updateAnimalFeedingSchedule(animal.getAnimalID(), animal.getFeedingScheduleId());
			animal = animalDao.getAllAnimals().get(0);
			System.out.println(animal);
			
			// Return animal to original schedule and remove test schedule from database
			animal.setFeedingSchedule(temp);
			dao.deleteFeedingSchedule(test.getScheduleId());
			dao.updateAnimalFeedingSchedule(animal.getAnimalID(), animal.getFeedingScheduleId());
			
			animal = animalDao.getAllAnimals().get(0);
			System.out.println(animal);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
	}
	
	static void testRemoveAnimalFeedingSchedule() {
		System.out.println("testRemoveAnimalFeedingSchedule:");
		
		FeedingScheduleDAOImpl dao = new FeedingScheduleDAOImpl();
		AnimalDaoImpl animalDao = new AnimalDaoImpl();
		Animal animal = animalDao.getAllAnimals().get(0);
		FeedingSchedule temp = animal.getFeedingSchedule();
		
		System.out.println(animal);
		try {
			dao.removeAnimalFeedingSchedule(animal.getAnimalID());
			animal = animalDao.getAllAnimals().get(0);
			System.out.println(animal);
			animal.setFeedingSchedule(temp);
			dao.updateAnimalFeedingSchedule(animal.getAnimalID(), animal.getFeedingScheduleId());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		
	}
}
