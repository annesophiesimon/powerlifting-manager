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

	// Save workout logs to a file
	public void saveWorkoutToFile() {
		// implementlogic to write workoutLog data to a file names
		// username_userId_workout.txt
	}

	// Retrieve workout data from a file
	public void loadWorkoutFromFile() {
		// Implement logic to read workoutLog data from userId_workout.txt
	}

}
