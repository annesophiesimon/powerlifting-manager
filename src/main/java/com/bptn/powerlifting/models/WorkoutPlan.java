package com.bptn.powerlifting.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkoutPlan extends WorkoutComponent {
	private int nbSet; // number of set
	private ExperienceLevel experienceLevel; // beginner, intermediate, advanced // this will determine the gap increase
												// in
	// between set

	// constructor
	public WorkoutPlan(String movementName, double targetWeight, int nbSet, ExperienceLevel experienceLevel) {
		super(movementName, targetWeight, LocalDate.now());
		this.nbSet = nbSet;
		this.experienceLevel = experienceLevel;
	}

	// getters

	public int getNbSet() {
		return this.nbSet;
	}

	public ExperienceLevel getExperienceLevel() {
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

	// calculate starting weight based on experienceLevel
	public double calculateStartingWeight() {
		return weight * experienceLevel.getStartingPercentage();
	}

	// method to display plan
	public void displayPlan(WorkoutPlan plan) {
		System.out.println("Your goal today is " + weight + " for " + movementName);
		List<Integer> sets = new ArrayList<>();
		// double startingPercentage;
		double startingPercentage = plan.getExperienceLevel().getStartingPercentage();

		int targetWeight = (int) plan.getWeight();
		int nbSet = plan.getNbSet();

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
