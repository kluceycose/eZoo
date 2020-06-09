package com.kluceycose.ezoo.dao;

import java.util.List;

import com.kluceycose.ezoo.model.Animal;
import com.kluceycose.ezoo.model.FeedingSchedule;

/**
 * Main interface used to execute CRUD methods on FeedingSchedule class
 * @author kluceycose
 *
 */
public interface FeedingScheduleDAO {
	/************/
	/** CREATE **/
	/************/
	
	/**
	 * Used to add a Feeding Schedule to the datastore
	 * @param scheduleToAdd
	 * @throws Exception 
	 */
	void addFeedingSchedule(FeedingSchedule scheduleToAdd) throws Exception;
	
	/**************/
	/** RETRIEVE **/
	/**************/
	
	/**
	 * Used to retrieve a list of all Feeding Schedules from the datastore
	 * @return List<FeedingSchedule>
	 * @throws Exception 
	 */
	List<FeedingSchedule> getAllFeedingSchedules();
	
	/**
	 * Used to retrieve a Feeding Schedule for a given animal from the datastore
	 * @param animal
	 * @return Feeding Schedule for this animal
	 */
	FeedingSchedule getFeedingSchedule(Animal animal);
	
	/************/
	/** UPDATE **/
	/************/
	
	/**
	 * Used to save/update a specific Feeding Schedule to the datastore
	 * @param updatedSchedule
	 * @throws Exception
	 */
	void saveFeedingSchedule(FeedingSchedule updatedSchedule) throws Exception;
	
	/**
	 * Used to update an Animal with a specific Feeding Schedule and save the change to the datastore
	 * @param animalid 	The ID of the animal
	 * @param scheduleid	The ID of the schedule
	 * @throws Exception
	 */
	void updateAnimalFeedingSchedule(long animalid, long scheduleid) throws Exception;
	
	/**
	 * Used to remove the Feeding Schedule from an Animal and save the change to the datastore
	 * @param animalid	The ID of the animal
	 * @throws Exception
	 */
	void removeAnimalFeedingSchedule(long animalid) throws Exception;
	
	/************/
	/** DELETE **/
	/************/
	
	/**
	 * Used to delete a specific Feeding Schedule from the datastore
	 * @param scheduleToDelete
	 * @throws Exception
	 */
	void deleteFeedingSchedule(long scheduleidToDelete) throws Exception;
	
}
