package com.bptn.powerlifting.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkoutPlan extends WorkoutComponent {
	private int nbSet; // number of set
	private String experienceLevel; // beginner, intermediate, advanced // this will determine the gap increase in
									// between set

	// constructor
	public WorkoutPlan(String movementName, double targetWeight, int nbSet, String experienceLevel) {
		super(movementName, targetWeight, LocalDate.now());
		this.nbSet = nbSet;
		this.experienceLevel = experienceLevel;
	}

	// getters

	public int getNbSet() {
		return this.nbSet;
	}

	public String getExperienceLevel() {
		return this.experienceLevel;
	}

	// display summary

	public void displaySummary() {
		System.out.println("************************************************************");
		System.out.println("*                  Powerlifting Program Summary            *");
		System.out.println("************************************************************");
		System.out.println("Movement: " + movementName);
		System.out.println("Target Weight: " + weight + " lbs");
		System.out.println("Experience Level: " + experienceLevel);
		System.out.println("Number of Sets: " + nbSet);
		System.out.println();
		System.out.println("Let's start your workout plan!");
		System.out.println("************************************************************");
	}

	// method to display plan
	public void displayPlan(WorkoutPlan plan) {
		System.out.println("Your goal today is " + weight + " for " + movementName);
		List<Integer> sets = new ArrayList<>();
		double startingPercentage;
		int targetWeight = (int) plan.getWeight();
		int nbSet = plan.getNbSet();
		String experienceLevel = plan.getExperienceLevel();
		switch (experienceLevel.toLowerCase()) {
		case "beginner":
			startingPercentage = 0.6;
			break;
		case "intermediate":
			startingPercentage = 0.7;
			break;
		case "advanced":
			startingPercentage = 0.8;
			break;
		default:
			startingPercentage = 0.75;
			break;
		}

		// calculate weight for each set
		// for a given number of set the last set will be the goal weight
		// the first set will have a different weight gap difference depending of the
		// level
		// starting weight
		int startingWeight = (int) (targetWeight * startingPercentage);
		// calculate weight Diff
		double weightDiff = targetWeight - startingWeight;
		double weightIncrement = weightDiff / (nbSet - 1);
		for (int i = 0; i < nbSet - 1; i++) {
			sets.add(startingWeight);
			startingWeight += weightIncrement;
		}
		// add target weight to set
		sets.add(targetWeight);
		// create an index as stream doesn't provide one
		AtomicInteger index = new AtomicInteger(1);
		sets.stream().forEach(set -> System.out.println("Set " + index.getAndIncrement() + ": " + set));
	}

	@Override
	public String getSummary() {
		return "Movement: " + movementName + ", Target Weight: " + weight + " lbs, Experience Level: " + experienceLevel
				+ ", Sets: " + nbSet;
	}

}
