package com.bptn.powerlifting.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;

public class InputUtils {

	// methods for input double
	public static Double getDoubleInput(Scanner scanner, String prompt, int min, int max) {
		Double input = 0.0;
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.println(prompt);
				input = scanner.nextDouble();
				if (input < min || input > max) {
					System.out.println("Please enter a weight between " + min + " and " + max + ".");
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.out.println(Ansi.ansi().fgRed().a("Invalid input. Please enter a valid weight.").fgMagenta());
				scanner.next(); // Clear invalid input
			}
		}
		return input;
	}

	// method for input integer
	public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
		int input = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.println(prompt);
				input = scanner.nextInt();

				if (input < min || input > max) {
					System.out.println("Please enter a number between " + min + " and " + max + ".");
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.out.println(Ansi.ansi().fgRed().a("Invalid input. Please enter a valid integer.").fgMagenta());
				scanner.next(); // Clear invalid input
			}
		}
		return input;
	}

}
