package com.bptn.powerlifting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.bptn.powerlifting.models.Barbell;
import com.bptn.powerlifting.models.Exercise;
import com.bptn.powerlifting.models.WorkoutLog;

public class PowerliftingAppTest {

	// test calculatePlateLoad class Barbell
	// initialize object
	private Barbell myBarbell;
	private WorkoutLog myWorkoutLog;

	@Test
	void testCalculatePlateLoad() {
		// create object
		myBarbell = new Barbell(300.0);
		myBarbell.initializePlates();

		// Define the expected result map
		Map<Double, Integer> expectedPlateLoad = new HashMap<>();
		expectedPlateLoad.put(10.0, 1);
		expectedPlateLoad.put(5.0, 1);
		expectedPlateLoad.put(2.5, 1);
		expectedPlateLoad.put(55.0, 2);

		// Run the method and compare the actual result with the expected result
		assertEquals(expectedPlateLoad, myBarbell.calculatePlateLoad(),
				"Plate load calculation should match expected output");
	}

	@Test
	void testGetDetailLog() {
		// create log
		Map<String, Exercise> log = new HashMap<>();
		Exercise exercice1 = new Exercise(300.0, 3, LocalDate.now());
		Exercise exercice2 = new Exercise(300.0, 3, LocalDate.now());

		log.put("Squat", exercice1);
		log.put("DeadLift", exercice2);

		// create object
		myWorkoutLog = new WorkoutLog(log);
		// Define the expected output for getDetailsLog
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Workout Log:\n");
		expectedOutput.append("Exercise: Squat\n").append(exercice1.getSummary()).append("\n");
		expectedOutput.append("Exercise: DeadLift\n").append(exercice2.getSummary()).append("\n");

		// Run the method and compare the result with the expected output
		assertEquals(expectedOutput.toString(), myWorkoutLog.getDetailsLog(),
				"Details log should match expected output");

	}

}