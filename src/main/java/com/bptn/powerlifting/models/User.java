package com.bptn.powerlifting.models;

public class User {
	private String username;
	private int userId = 0;
	private WorkoutLog workoutLog;

	public User(String username) {
		this.username = username;
		this.userId += 1;
		this.workoutLog = new WorkoutLog();
	}

	// getter
	public String getUsername() {
		return username;
	}

	public int userId() {
		return userId;
	}

	public WorkoutLog getWorkoutLog() {
		return workoutLog;
	}

}
