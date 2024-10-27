package com.bptn.powerlifting.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.fusesource.jansi.Ansi;

import com.bptn.powerlifting.models.Exercise;
import com.bptn.powerlifting.models.WorkoutLog;

public class FileUtils {

	public static void saveWorkoutLog(String workoutLogDetails, String filepath) {
		try {
			FileWriter writer = new FileWriter(filepath, true);
			writer.write(workoutLogDetails);
			System.out.println(Ansi.ansi().fgGreen().a("Workout successfuly logged").fgMagenta());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void displayWorkoutLog(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(filepath);
				BufferedReader bufferredReader = new BufferedReader(reader);
				String line;
				while ((line = bufferredReader.readLine()) != null) {
					System.out.println(line);

				}
			} catch (IOException e) {
				System.out.println(Ansi.ansi().fgRed().a("Something went wrong" + e.getMessage()).fgMagenta());
				e.printStackTrace();
			}

		} else {
			System.out.println("Sorry, you don't have workout logged yet, please save your workout first");
		}

	}

	public static void loadWorkoutLog(WorkoutLog workoutLog, String filePath) {
		File file = new File(filePath);

		if (file.exists()) {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
				String line;
				String currentExercise = null;

				while ((line = bufferedReader.readLine()) != null) {
					// Check if the line indicates an exercise name
					if (line.startsWith("Exercise: ")) {
						currentExercise = line.substring(10).trim(); // Extract the exercise name
					} else if (line.startsWith("Weight: ") && currentExercise != null) {
						// Parse the weight, reps, and date from the line
						String[] parts = line.split(", ");
						double weight = Double.parseDouble(parts[0].split(": ")[1].replace(" lbs", ""));
						int reps = Integer.parseInt(parts[1].split(": ")[1]);
						LocalDate date = LocalDate.parse(parts[2].split(": ")[1]);

						// Create an Exercise object and add it to the WorkoutLog
						Exercise exercice = new Exercise(weight, reps, date);
						workoutLog.addExercise(currentExercise, exercice);

					}
				}

			} catch (IOException e) {
				System.out.println("Something went wrong: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			Ansi.ansi().fgBrightBlue().a("No previous data found. Start logging your workouts!").fgMagenta();
		}
	}

}
