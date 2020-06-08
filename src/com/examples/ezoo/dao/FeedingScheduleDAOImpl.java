package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
	
	public FeedingScheduleDAOImpl() {}

	@Override
	public void addFeedingSchedule(FeedingSchedule scheduleToAdd) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO feeding_schedules VALUES (?,?,?,?,?,?)";
			
			statement = connection.prepareStatement(sql);
			
			// Add parameters from scheduleToAdd into PreparedStatement
			statement.setLong(1, scheduleToAdd.getScheduleId());
			statement.setString(2, scheduleToAdd.getFeedingTime());
			statement.setString(3, scheduleToAdd.getRecurrence());
			statement.setString(4, scheduleToAdd.getFood());
			statement.setString(5, scheduleToAdd.getNotes());
			statement.setString(6, scheduleToAdd.getName());
			
			success = statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(success == 0) {
			// update didn't occur, throw an exception
			throw new Exception("Insert scheduleToAdd failed: " + scheduleToAdd);
		}
		
	}

	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() {
		Connection connection = null;
		Statement statement = null;
		List<FeedingSchedule> schedules = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedules";
			
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				FeedingSchedule fs = new FeedingSchedule();
				
				fs.setScheduleId(rs.getLong("schedule_id"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				fs.setName(rs.getString("name"));
				
				schedules.add(fs);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return schedules;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		Connection connection = null;
		PreparedStatement statement = null;
		FeedingSchedule schedule = new FeedingSchedule();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedules WHERE schedule_id = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setLong(1, animal.getFeedingScheduleId());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				schedule.setScheduleId(rs.getLong("schedule_id"));
				schedule.setFeedingTime(rs.getString("feeding_time"));
				schedule.setRecurrence(rs.getString("recurrence"));
				schedule.setFood(rs.getString("food"));
				schedule.setNotes(rs.getString("notes"));
				schedule.setName(rs.getString("name"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return schedule;
	}

	@Override
	public void saveFeedingSchedule(FeedingSchedule updatedSchedule) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE feeding_schedules SET feeding_time = ?, recurrence = ?, food = ?, notes = ?, name = ? WHERE schedule_id = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, updatedSchedule.getFeedingTime());
			statement.setString(2, updatedSchedule.getRecurrence());
			statement.setString(3, updatedSchedule.getFood());
			statement.setString(4, updatedSchedule.getNotes());
			statement.setString(5, updatedSchedule.getName());
			statement.setLong(6, updatedSchedule.getScheduleId());
			
			
			success = statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(success == 0) {
			// update didn't occur, throw an exception
			throw new Exception("Update updatedSchedule failed: " + updatedSchedule);
		}

	}

	@Override
	public void updateAnimalFeedingSchedule(long animalid, long scheduleid) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql;
			
			if(scheduleid < 0L) {
				sql = "UPDATE animals SET feeding_schedule = null WHERE animalid = ?";
			} else {
				sql = "UPDATE animals SET feeding_schedule = ? WHERE animalid = ?";
			}
			
			statement = connection.prepareStatement(sql);
			
			if(scheduleid < 0L) {
				statement.setLong(1, animalid);
			} else {
				statement.setLong(1, scheduleid);				
				statement.setLong(2, animalid);				
			}
			
			success = statement.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(success == 0) {
			// update didn't occur, throw an exception
			throw new Exception("Update animal with new feedingSchedule failed: \nanimalid: " + animalid + ", scheduleid: " + scheduleid);
		}

	}

	@Override
	public void removeAnimalFeedingSchedule(long animalid) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule = null WHERE animalid = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setLong(1, animalid);
			
			success = statement.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(success == 0) {
			// update didn't occur, throw an exception
			throw new Exception("Remove animal's feeding schedule failed: animalid- " + animalid);
		}

	}

	@Override
	public void deleteFeedingSchedule(long scheduleidToDelete) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		int success1 = 0;
		int success2 = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			// Remove references to this feeding schedule from all animals
			String sql = "UPDATE animals SET feeding_schedule = null WHERE feeding_schedule = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setLong(1, scheduleidToDelete);
			
			success1 = statement.executeUpdate();
			statement.close();
			
			// Delete feeding schedule
			sql = "DELETE FROM feeding_schedules WHERE schedule_id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setLong(1, scheduleidToDelete);
			
			success2 = statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(success1 == 0) {
			// update didn't occur, throw an exception
			System.out.println("Removed scheduleToDelete from 0 animals: " + scheduleidToDelete);
		}
		if(success2 == 0) {
			// update didn't occur, throw and exception
			throw new Exception("Delete scheduleToDelete failed: " + scheduleidToDelete);
		}

	}

}
