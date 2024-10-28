package com.bptn.powerlifting;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;

import com.bptn.powerlifting.models.Advanced;
import com.bptn.powerlifting.models.Barbell;
import com.bptn.powerlifting.models.Beginner;
import com.bptn.powerlifting.models.Exercise;
import com.bptn.powerlifting.models.ExperienceLevel;
import com.bptn.powerlifting.models.Intermediate;
import com.bptn.powerlifting.models.User;
import com.bptn.powerlifting.models.WorkoutLog;
import com.bptn.powerlifting.models.WorkoutPlan;
import com.bptn.powerlifting.utils.FileUtils;
import com.bptn.powerlifting.utils.InputUtils;

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
			System.out.println("[2] View Workout");
			System.out.println("[3] Log Workout");
			System.out.println("[4] View Personal Bests");
			System.out.println("[5] Barbell Loading Guide");
			System.out.println("[6] Exit");
			System.out.println();
			userChoice = InputUtils.getIntInput(scanner, "Please select an option (1-6): ", 1, Integer.MAX_VALUE);

			System.out.println();
			System.out.println("************************************************************");

			switch (userChoice) {
			case 1:
				System.out.println("Plan a workout");
				WorkoutPlan plan = createWorkoutPlan(scanner);
				plan.displaySummary();
				plan.displayPlan(plan);
				break;
			case 2:
				System.out.println("View Workout");
				viewProgress(scanner);
				break;
			case 3:
				System.out.println("Log Workout");
				logWorkout(scanner);
				break;
			case 4:
				System.out.println("view personal best");
				viewBestPerformance(scanner);

				break;
			case 5:
				System.out.println("Barbell Loading Guide");
				createBarbellLoad(scanner);

				break;
			case 6:
				System.out.println("Thank you for using the powerlifting app!");
				break;
			}

		} while (userChoice != 6);
		// close scanner
		scanner.close();

	}

	private static WorkoutPlan createWorkoutPlan(Scanner scanner) {
		// Ask movement type
		String movementName = getMovementName(scanner);
		// Ask for target weight
		int goalWeight = InputUtils.getIntInput(scanner, "Enter your target weight for " + movementName + " (in lbs): ",
				80, 900);

		// Ask for experience level
		System.out.println("Select your experience level:");
		System.out.println("[1] Beginner");
		System.out.println("[2] Intermediate");
		System.out.println("[3] Advanced");
		int experienceChoice = InputUtils.getIntInput(scanner, "Enter your choice (1-3): ", 1, 3);
		// Define experience level
		ExperienceLevel experienceLevel = switch (experienceChoice) {
		case 1 -> new Beginner();
		case 2 -> new Intermediate();
		case 3 -> new Advanced();
		default -> new Intermediate(); // Default to Intermediate if invalid input
		};

		// Ask for the number of sets
		int numSets = InputUtils.getIntInput(scanner, "Enter the number of sets you'd like to perform (e.g., 4): ", 2,
				10);

		// Create and return the WorkoutPlan object
		return new WorkoutPlan(movementName, goalWeight, numSets, experienceLevel);
	}

	private static void createBarbellLoad(Scanner scanner) {
		// Ask target weight
		double targetWeight = 0.0;
		// Valid input
		boolean validInput = false;
		while (!validInput) {

			try {
				System.out.println("Please enter the weight of your choice");
				targetWeight = scanner.nextDouble();

				// Check if the input is whitin the allowed range
				if (targetWeight > 900) {
					System.out.println("The weight entered is too high. Please enter a value less than 900 lbs.");

				} else if (targetWeight > 0 && targetWeight <= 60) {
					System.out.println("Please use an empty bar or body weight for your session.");
					return; // return as user entered a valid number but don't need the BarbellLoad help

				} else if (targetWeight <= 0) {
					System.out.println("Please enter a positive value");

				} else {
					validInput = true; // break out the if input is valid
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a numeric value for the weight.");
				scanner.next(); // Clear the invalid input
			}
		}

		Barbell barbell = new Barbell(targetWeight);

		// calculate plate load
		Map<Double, Integer> plateLoad = barbell.calculatePlateLoad();

		// Display the recommendation
		barbell.displayPlateRecommendation(plateLoad);

	}

	private static void logWorkout(Scanner scanner) {

		// username variable
		String username = getUsername(scanner, "Enter your username to log your workout");
		String filePath = username + ".txt";
		// create user object
		User user = new User(username);

		WorkoutLog workoutLog = user.getWorkoutLog();
		// userChoice
		int userChoice;
		// Create Exercise to add in WorkoutLog
		// Get movement name from user input
		// Loop do while to create exercise as many time as user want
		do {
			String movementName = getMovementName(scanner);
			Double weightLifted = InputUtils.getDoubleInput(scanner,
					"Please type in the weight lifted for " + movementName + " today", 5, 900);
			System.out.println();
			int reps = InputUtils.getIntInput(scanner, "Type the number of reps, between 1-50", 1, 50);
			LocalDate todayDate = LocalDate.now();
			// Create exercise object
			Exercise exercise = new Exercise(weightLifted, reps, todayDate);

			// Add the exercise to workoutlog
			workoutLog.addExercise(movementName, exercise);
			userChoice = InputUtils.getIntInput(scanner,
					"Would you like to add a new exercice? type 1 continue or 2 to stop:", 1, Integer.MAX_VALUE);

		} while (userChoice == 1);

		// save workout to file
		// Display the workout logged
		FileUtils.saveWorkoutLog(workoutLog.getDetailsLog(), filePath);

	}

	// method to view workout log
	private static void viewProgress(Scanner scanner) {
		// username variable
		String username = getUsername(scanner,
				"Enter your username to display your previous workout and analyze your progress");
		String filePath = username + ".txt";
		FileUtils.displayWorkoutLog(filePath);

	}

	// method to view personal best
	private static void viewBestPerformance(Scanner scanner) {
		String username = getUsername(scanner, "Enter your username to display your PR (personal record)");
		String filePath = username + ".txt";
		User user = new User(username);
		WorkoutLog workoutLog = user.getWorkoutLog();
		FileUtils.loadWorkoutLog(workoutLog, filePath);
		System.out.println(workoutLog.displayPersonalBests());
	}

	// create function to reuse the code
	private static String getMovementName(Scanner scanner) {
		// Ask movement type
		String movement = "";
		// condition to loop until user provide correct input
		while (true) {
			System.out.println("Please select a movement:");
			System.out.println("[1] Squat");
			System.out.println("[2] Bench Press");
			System.out.println("[3] Deadlift");
			// System.out.print("Enter your choice (1-3): ");
			int movementChoice = InputUtils.getIntInput(scanner, "Enter your choice (1-3): ", 1, 3);

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

	private static String getUsername(Scanner scanner, String prompt) {
		System.out.println(prompt);
		// username variable
		String username = scanner.next();
		System.out.println("Welcome, " + username.substring(0, 1).toUpperCase() + username.substring(1) + "\n\n");

		return username;
	}

}
