package com.bptn.powerlifting.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static void saveWorkoutLog(String workoutLogDetails, String filepath) {
		try {
			FileWriter writer = new FileWriter(filepath, true);
			writer.write(workoutLogDetails);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
