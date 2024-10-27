package com.bptn.powerlifting.models;

import java.time.LocalDate;

public class Exercise extends WorkoutComponent {
	// private double weight;
	private int reps;
	// private LocalDate date;

	public Exercise(double weight, int reps, LocalDate date) {
		// this.weight = weight;
		super(weight, date);
		this.reps = reps;
		// this.date = date;
	}

	// add getter

	public int getReps() {
		return reps;
	}

	@Override
	public String getSummary() {
		return "Weight: " + weight + " lbs, Reps: " + reps + ", Date: " + date;
	}

}
