package com.bptn.powerlifting.models;

import java.time.LocalDate;

public abstract class WorkoutComponent {
	protected String movementName;
	protected double weight;
	protected LocalDate date;

	public WorkoutComponent(String movementName, double weight, LocalDate date) {
		this.movementName = movementName;
		this.weight = weight;
		this.date = date;
	}

	public WorkoutComponent(double weight, LocalDate date) {
		this.weight = weight;
		this.date = date;
	}

	// getter methods

	public String getMovementName() {
		return movementName;
	}

	public double getWeight() {
		return weight;
	}

	public LocalDate getDate() {
		return date;
	}

	// summary method
	public abstract String getSummary();

}
