package com.bptn.powerlifting.models;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WorkoutLog {

	private Map<String, Exercise> log;

	public WorkoutLog() {
		this.log = new HashMap<>();

	}

	public WorkoutLog(Map<String, Exercise> log) {
		this.log = log;
	}

	public void addExercise(String movementName, Exercise exercise) {
		this.log.put(movementName, exercise);
	}

	public String getDetailsLog() {
		StringBuilder sb = new StringBuilder();

		sb.append("Workout Log:\n");
		for (Map.Entry<String, Exercise> entry : log.entrySet()) {
			sb.append("Exercise: ").append(entry.getKey()).append("\n");
			sb.append(entry.getValue().getSummary()).append("\n");
		}

		return sb.toString();
	}

	// display personal best

	public void displayPersonalBests() {
		if (log.isEmpty()) {
			System.out.println("No workout data available.");
			return;
		}

		// Find the Exercise with the highest weight for the movement
		Exercise best = log.values().stream().max(Comparator.comparingDouble(Exercise::getWeight)).orElse(null);

		// Display the best exercise details if found
		if (best != null) {
			System.out.println("Personal Best for: ");
			System.out.println(best.getSummary());
			/*
			 * System.out.println( "Best Lift: " + best.getWeight() + " lbs, Reps: " +
			 * best.getReps() + ", Date: " + best.getDate());
			 */
		} else {
			System.out.println("No exercises logged.");
		}

	}

}
