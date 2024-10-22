package com.bptn.powerlifting.models;

import java.util.HashMap;
import java.util.Map;

public class WorkoutLog {

	private String movement;
	private Map<String, Exercice> log;

	public WorkoutLog() {
		this.log = new HashMap<>();

	}

	public WorkoutLog(String movement, Map<String, Exercice> log) {
		this.movement = movement;
		this.log = log;
	}

	public void addExercice(String movementName, Exercice exercice) {
		this.log.put(movementName, exercice);
	}

	public void displayLog() {
		System.out.println("Workout Log: ");
		for (Map.Entry<String, Exercice> entry : log.entrySet()) {
			System.out.println("Exercise: " + entry.getKey());
			entry.getValue().display();
		}
	}

}
