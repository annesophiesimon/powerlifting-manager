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

	public String getDetailsLog() {
		StringBuilder sb = new StringBuilder();

		sb.append("Workout Log:\n");
		for (Map.Entry<String, Exercice> entry : log.entrySet()) {
			sb.append("Exercise: ").append(entry.getKey()).append("\n");
			sb.append(entry.getValue().toString()).append("\n"); // Ensure Exercice's toString is well formatted
		}

		return sb.toString();
	}

}
