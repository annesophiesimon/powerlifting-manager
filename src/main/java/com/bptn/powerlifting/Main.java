package com.bptn.powerlifting;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;

import com.bptn.powerlifting.models.Barbell;
import com.bptn.powerlifting.models.Exercise;
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
			// System.out.print("Please select an option (1-6): ____");
			userChoice = InputUtils.getIntInput(scanner, "Please select an option (1-6): ", 1, Integer.MAX_VALUE);

			System.out.println();
			System.out.println("************************************************************");

			// userChoice = scanner.nextInt();
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
			/*
			 * default: System.out.
			 * println("Sorry we didn't understand your request please contact customer service"
			 * );
			 */
			}

		} while (userChoice != 6);
		// close scanner
		scanner.close();

	}

	private static WorkoutPlan createWorkoutPlan(Scanner scanner) {
		// ask movement type
		String movementName = getMovementName(scanner);
		// ask for target weight
		int goalWeight = InputUtils.getIntInput(scanner, "Enter your target weight for " + movementName + " (in lbs): ",
				80, 900);

		// ask for experience level
		System.out.println("Select your experience level:");
		System.out.println("[1] Beginner");
		System.out.println("[2] Intermediate");
		System.out.println("[3] Advanced");
		int experienceChoice = InputUtils.getIntInput(scanner, "Enter your choice (1-3): ", 1, 3);
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
		int numSets = InputUtils.getIntInput(scanner, "Enter the number of sets you'd like to perform (e.g., 4): ", 2,
				10);

		// Create and return the WorkoutPlan object
		return new WorkoutPlan(movementName, goalWeight, numSets, experienceLevel);
	}

	private static void createBarbellLoad(Scanner scanner) {
		// ask target weight
		double targetWeight = 0.0;
		// valid input
		boolean validInput = false;
		while (!validInput) {

			try {
				System.out.println("Please enter the weight of your choice");
				// TO DO: handle try/catch for wrong input
				targetWeight = scanner.nextDouble();

				// check if the input is whitin the allowed range
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
		// login or create user
		System.out.println("Enter your username to continue logging your progress.");
		// username variable
		String username = scanner.next();
		System.out.println("Welcome, " + username.substring(0, 1).toUpperCase() + username.substring(1));
		String filePath = username + ".txt";
		// create user object
		User user = new User(username);

		WorkoutLog workoutLog = user.getWorkoutLog();
		// userChoice
		int userChoice;

		// 1/ create Exercise to add in WorkoutLog
		// get movement name from user input
		// loop do while to create exercise as much as user want
		do {
			String movementName = getMovementName(scanner);
			Double weightLifted = InputUtils.getDoubleInput(scanner,
					"Please type in the weight lifted for " + movementName + " today", 5, 900);
			System.out.println();
			int reps = InputUtils.getIntInput(scanner, "Type the number of reps", 1, 50);
			LocalDate todayDate = LocalDate.now();
			// create exercise object
			Exercise exercise = new Exercise(weightLifted, reps, todayDate);

			// add the exercise to workoutlog
			workoutLog.addExercise(movementName, exercise);
			userChoice = InputUtils.getIntInput(scanner, "Would you like to add a new exercice? type 1", 1,
					Integer.MAX_VALUE);

		} while (userChoice == 1);

		// Display the workout logged
		System.out.println(workoutLog.getDetailsLog());
		FileUtils.saveWorkoutLog(workoutLog.getDetailsLog(), filePath);

	}

	// method to view workout log
	private static void viewProgress(Scanner scanner) {
		System.out.println("Enter your username view your workout");
		// username variable
		String username = scanner.next();
		System.out.println("Welcome, " + username.substring(0, 1).toUpperCase() + username.substring(1));
		String filePath = username + ".txt";
		FileUtils.displayWorkoutLog(filePath);

	}

	// method to view personal best
	private static void viewBestPerformance(Scanner scanner) {
		System.out.println("Enter your username to continue logging your progress.");
		// username variable
		String username = scanner.next();
		System.out.println("Welcome, " + username.substring(0, 1).toUpperCase() + username.substring(1));
		String filePath = username + ".txt";
		User user = new User(username);
		WorkoutLog workoutLog = user.getWorkoutLog();
		FileUtils.loadWorkoutLog(workoutLog, filePath);
		workoutLog.displayPersonalBests();
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

}
