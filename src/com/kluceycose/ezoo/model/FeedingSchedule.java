package com.kluceycose.ezoo.model;

import java.util.Set;
import java.util.TreeSet;

public class FeedingSchedule {
	private long scheduleId = -1L;
	private String feedingTime = "";
	private String recurrence = "";
	private String food = "";
	private String notes = "";
	private String name = "None";
	private Set<Animal> assignedAnimals = new TreeSet<>();

	public FeedingSchedule(){}
	
	public FeedingSchedule(long scheduleID, String feedingTime, String recurrence, String food, String notes, String name){
		super();
		this.scheduleId = scheduleID;
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
		this.name = name;
	}
	
	/***********/
	/* SETTERS */
	/***********/
	
	public void setScheduleId(long scheduleID) {
		this.scheduleId = scheduleID;
	}
	
	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}
	
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	
	public void setFood(String food) {
		this.food = food;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAssignedAnimals(Set<Animal> assignedAnimals) {
		this.assignedAnimals = assignedAnimals;
	}
	
	/***********/
	/* GETTERS */
	/***********/
	
	public long getScheduleId() {
		return scheduleId;
	}
	
	public String getFeedingTime() {
		return feedingTime;
	}

	public String getRecurrence() {
		return recurrence;
	}
	
	public String getFood() {
		return food;
	}

	public String getNotes() {
		return notes;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Animal> getAssignedAnimals() {
		return assignedAnimals;
	}
	
	@Override
	public String toString() {
		String aaText = "none";
		if(!assignedAnimals.isEmpty()) {
			aaText = assignedAnimals.toString();
		}
		return "Feeding Schedule [scheduleId: " + scheduleId + ", name: " + name + ", feedingTime: " + feedingTime + ", recurrence: "
				+ recurrence + ", food: " + food + ", notes: " + notes + "assigned animals: " + aaText + "]";
	}
}
