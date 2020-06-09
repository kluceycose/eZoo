package com.kluceycose.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Class used to retrieve DAO Implementations. Serves as a factory.
 * 
 * @author anon
 *
 */

@Configuration
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "kluceycose2020";
	private static final String URL = "jdbc:postgresql://localhost:5433/eZoo";
	
	private static AnimalDaoImpl animalDaoImpl;
	private static FeedingScheduleDAOImpl feedingScheduleDaoImpl;
	private static Connection connection;

	@Bean
	public AnimalDAO getAnimalDao() {

		if (animalDaoImpl == null) {
			animalDaoImpl = new AnimalDaoImpl();
		}
		return animalDaoImpl;
	}
	
	@Bean
	public FeedingScheduleDAO getFeedingScheduleDao() {
		if(feedingScheduleDaoImpl == null) {
			feedingScheduleDaoImpl = new FeedingScheduleDAOImpl();
		}
		return feedingScheduleDaoImpl;
	}

	@Bean
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

}
