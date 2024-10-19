package com.bptn.powerlifting;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// create a scanner object
		Scanner scanner = new Scanner(System.in);
		// Variable user choice
		int userChoice;
		// Menu loop
		do {

			System.out.println("************************************************************");
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
				break;
			case 2:
				System.out.println("View Progress");
				break;
			case 3:
				System.out.println("Log Workout");
				break;
			case 4:
				System.out.println("View Personal Bests");
				break;
			case 5:
				System.out.println("Barbell Loading Guide");
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

}
