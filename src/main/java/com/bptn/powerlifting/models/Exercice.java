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

	@Override
	public String toString() {
		return "Weight: " + weight + " lbs, Reps: " + reps + ", Date: " + date;
	}

}
