package com.bptn.powerlifting.models;

import java.time.LocalDate;

public class Exercice {
	private double weight;
	private int reps;
	private LocalDate date;

	public Exercice(double weight, int reps, LocalDate date) {
		this.weight = weight;
		this.reps = reps;
		this.date = date;
	}

	// add getter
	public double getWeight() {
		return weight;
	}

	public int getReps() {
		return reps;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Weight: " + weight + " lbs, Reps: " + reps + ", Date: " + date;
	}

}
