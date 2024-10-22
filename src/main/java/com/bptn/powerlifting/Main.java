package com.bptn.powerlifting;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;

import com.bptn.powerlifting.models.Barbell;
import com.bptn.powerlifting.models.Exercice;
import com.bptn.powerlifting.models.WorkoutLog;
import com.bptn.powerlifting.models.WorkoutPlan;

public class Main {

	public static void main(String[] args) {
		// create a scanner object
		Scanner scanner = new Scanner(System.in);
		// Variable user choice
		int userChoice;
		// Menu loop
		do {

			System.out
					.println(Ansi.ansi().fgMagenta().a("************************************************************"));
			System.out.println("*                   Powerlifting App Main Menu             *");
			System.out.println("************************************************************");
			System.out.println("Welcome!");
			System.out.println();
			System.out.println("[1] Plan a Workout");
			System.out.println("[2] View Progress");
			System.out.println("[3] Log Workout");
			System.out.println("[4] View Personal Bests");
			System.out.println("[5] Barbell Loading Guide");
			System.out.println("[6] Exit");
			System.out.println();
			System.out.print("Please select an option (1-6): ____");
			System.out.println();
			System.out.println("************************************************************");

			userChoice = scanner.nextInt();
			switch (userChoice) {
			case 1:
				System.out.println("Plan a workout");
				WorkoutPlan plan = createWorkoutPlan(scanner);
				plan.displaySummary();
				plan.displayPlan(plan);
				break;
			case 2:
				System.out.println("View Progress");
				break;
			case 3:
				System.out.println("Log Workout");
				logWorkout(scanner);
				break;
			case 4:
				System.out.println("View Personal Bests");
				break;
			case 5:
				System.out.println("Barbell Loading Guide");
				createBarbellLoad(scanner);

				break;
			case 6:
				System.out.println("Exit");
				break;
			default:
				System.out.println("Sorry we didn't understand your request please contact customer service");
			}

		} while (userChoice != 6);
		// close scanner
		scanner.close();

	}

	private static WorkoutPlan createWorkoutPlan(Scanner scanner) {
		// ask movement type
		String movementName = getMovementName(scanner);
		// ask for target weight
		System.out.print("Enter your target weight for " + movementName + " (in lbs): ");
		int goalWeight = scanner.nextInt();

		// ask for experience level
		System.out.println("Select your experience level:");
		System.out.println("[1] Beginner");
		System.out.println("[2] Intermediate");
		System.out.println("[3] Advanced");
		System.out.print("Enter your choice (1-3): ");
		int experienceChoice = scanner.nextInt();
		String experienceLevel = "";

		switch (experienceChoice) {
		case 1:
			experienceLevel = "Beginner";
			break;
		case 2:
			experienceLevel = "Intermediate";
			break;
		case 3:
			experienceLevel = "Advanced";
			break;
		default:
			System.out.println("Invalid choice. We will applicate Intermediate level by default");
		}

		// ask for the number of sets
		System.out.print("Enter the number of sets you'd like to perform (e.g., 4): ");
		int numSets = scanner.nextInt();

		// Create and return the WorkoutPlan object
		return new WorkoutPlan(movementName, goalWeight, numSets, experienceLevel);
	}

	private static void createBarbellLoad(Scanner scanner) {
		// ask target weight
		double targetWeight = 0.0;
		System.out.println("Please enter the weight of your choice");
		// TO DO: handle try/catch for wrong input
		targetWeight = scanner.nextDouble();
		System.out.println();

		Barbell barbell = new Barbell(targetWeight);

		Map<Double, Integer> plateLoad = barbell.calculatePlateLoad();
		barbell.displayPlateRecommendation(plateLoad);

	}

	private static void logWorkout(Scanner scanner) {
		WorkoutLog workoutLog = new WorkoutLog();
		// userChoice
		int userChoice;

		// 1/ create exercice to add in WorkoutLog
		// get movement name from user input
		// loop do while to create exercice as much as user want
		do {
			String movementName = getMovementName(scanner);
			System.out.println("Please type in the weight lifted for " + movementName + " today");
			Double weightLifted = scanner.nextDouble();
			System.out.println();
			System.out.println("Type the number of reps");
			int reps = scanner.nextInt();
			LocalDate todayDate = LocalDate.now();
			// create exercice object
			Exercice exercice = new Exercice(weightLifted, reps, todayDate);

			// add the exercice to workoutlog
			workoutLog.addExercice(movementName, exercice);
			System.out.println("Would you like to add a new exercice? type 1");
			userChoice = scanner.nextInt();

		} while (userChoice == 1);

		// Display the workout logged
		workoutLog.displayLog();

	}

	// create function to reuse the code
	private static String getMovementName(Scanner scanner) {
		// ask movement type
		String movement = "";
		// condition to loop until user provide correct input
		while (true) {
			System.out.println("Please select a movement:");
			System.out.println("[1] Squat");
			System.out.println("[2] Bench Press");
			System.out.println("[3] Deadlift");
			System.out.print("Enter your choice (1-3): ");
			int movementChoice = scanner.nextInt();
			System.out.println();

			if (movementChoice == 1) {
				movement = "Squat";
				break;
			} else if (movementChoice == 2) {
				movement = "Bench Press";
				break;
			} else if (movementChoice == 3) {
				movement = "Deadlift";
				break;
			} else {
				System.out.println("Invalid choice. Please enter a number between 1 and 3.");
			}
		}
		return movement;
	}

}
