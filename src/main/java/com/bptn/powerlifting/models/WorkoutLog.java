package com.bptn.powerlifting.models;

import java.util.Comparator;
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
			sb.append(entry.getValue().toString()).append("\n");
		}

		return sb.toString();
	}

	// display personal best

	public void displayPersonalBests() {
		if (log.isEmpty()) {
			System.out.println("No workout data available.");
			return;
		}

		// Find the Exercice with the highest weight for the movement
		Exercice best = log.values().stream().max(Comparator.comparingDouble(Exercice::getWeight)).orElse(null);

		// Display the best exercise details if found
		if (best != null) {
			System.out.println("Personal Best for: ");
			System.out.println(
					"Best Lift: " + best.getWeight() + " lbs, Reps: " + best.getReps() + ", Date: " + best.getDate());
		} else {
			System.out.println("No exercises logged.");
		}

	}

}
