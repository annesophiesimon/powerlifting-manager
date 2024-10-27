package com.bptn.powerlifting.models;

import java.time.LocalDate;

public class Exercise extends WorkoutComponent {
	private int reps;

	public Exercise(double weight, int reps, LocalDate date) {
		super(weight, date);
		this.reps = reps;
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
