package com.bptn.powerlifting.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fusesource.jansi.Ansi;

public class WorkoutLog {

	private Map<String, List<Exercise>> log;

	public WorkoutLog() {
		this.log = new HashMap<>();
	}

	public WorkoutLog(Map<String, List<Exercise>> log) {
		this.log = log;
	}

	// getter
	public Map<String, List<Exercise>> getLog() {
		return this.log;
	}

	public void addExercise(String movementName, Exercise exercise) {
		// Check if the list for the movement already exists
		if (!log.containsKey(movementName)) {
			log.put(movementName, new ArrayList<>());
		}
		// Add the exercise to the list for the movement
		log.get(movementName).add(exercise);
	}

	public String getDetailsLog() {
		StringBuilder sb = new StringBuilder();

		sb.append("Workout Log:\n");
		for (Map.Entry<String, List<Exercise>> entry : log.entrySet()) {
			sb.append("Exercise: ").append(entry.getKey()).append("\n");
			for (Exercise exercise : entry.getValue()) {
				sb.append(exercise.getSummary()).append("\n");
			}
		}

		return sb.toString();
	}

	public String displayPersonalBests() {
		// Check if log is empty to display they don't have workout available to
		// Calculate bests record
		if (log.isEmpty()) {
			return Ansi.ansi().fgRed().a("No workout data available.").fgMagenta().toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Personal Bests:\n");
		for (Map.Entry<String, List<Exercise>> entry : log.entrySet()) {
			String movementName = entry.getKey();
			List<Exercise> exercises = entry.getValue();

			// Find the PR (personal record) for each movement
			Exercise best = exercises.stream().max(Comparator.comparingDouble(Exercise::getWeight)).orElse(null);

			if (best != null) {
				sb.append("Movement: ").append(movementName).append("\n");
				sb.append(best.getSummary()).append("\n");
			}
		}
		return sb.toString().trim();
	}
}
