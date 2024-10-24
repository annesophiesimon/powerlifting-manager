package com.bptn.powerlifting.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fusesource.jansi.Ansi;

public class Barbell {
	private double barbellWeight;
	private Map<Double, String> availablePlate;
	private Double targetWeight;

	// constructor
	public Barbell(Double targetWeight) {
		barbellWeight = 45.0;
		availablePlate = initializePlates();
		this.targetWeight = targetWeight;
	}

// initialize availablePlate 

	private Map<Double, String> initializePlates() {
		Map<Double, String> plates = new HashMap<>();
		plates.put(55.0, "Red");
		plates.put(45.0, "Blue");
		plates.put(35.0, "Yellow");
		plates.put(25.0, "Green");
		plates.put(10.0, "White");
		plates.put(5.0, "Blue");
		plates.put(2.5, "Green");
		plates.put(1.25, "White");

		return plates;
	}

// calculate plate method 
	public Map<Double, Integer> calculatePlateLoad() {

		// HashMap for the result per side
		Map<Double, Integer> platePerSide = new HashMap<>();

		// calculate the weight to load(plates) in the barbell
		// target weight minus the barbellWeight will give the weight to charge in
		// plates
		// divide the value/2 as we will charge each side equally
		double weightToLoadPerSide = (targetWeight - barbellWeight) / 2;

		// In powerlifting we load heaviest plates first so we need to have the plates
		// in descending order
		// Loop over plates
		for (Double plateWeight : availablePlate.keySet().stream().sorted((a, b) -> Double.compare(b, a)).toList()) {
			// initialize count (number of plates)
			int count = 0;

			while (weightToLoadPerSide - plateWeight >= 0) {
				weightToLoadPerSide -= plateWeight; // substract the weight of the plate
				count += 1; // add 1 (for each side)

			}
			// if we used a plate add it to the platePerSide Map
			if (count > 0) {
				platePerSide.put(plateWeight, count);
			}

		}
		return platePerSide;
	}

	// display plate recommendation
	public void displayPlateRecommendation(Map<Double, Integer> platePerSide) {
		// append the string with the barbell and match with the color of availablePlate
		String platesSetup = "";
		// sort keys in descending order
		List<Double> sortedPlates = platePerSide.keySet().stream().sorted((a, b) -> Double.compare(b, a)).toList();
		// Map to convert color strings to Ansi.Color values
		Map<String, Ansi.Color> colorMapping = Map.of("Red", Ansi.Color.RED, "Blue", Ansi.Color.BLUE, "Green",
				Ansi.Color.GREEN, "White", Ansi.Color.WHITE, "Yellow", Ansi.Color.YELLOW);
		// Loop through the sorted array
		for (Double plateWeight : sortedPlates) {
			int count = platePerSide.get(plateWeight);
			String color = availablePlate.get(plateWeight);
			// Get the Ansi.Color value from the map using the color name
			// display each plate with the ansi color corresponding to color plate
			Ansi.Color ansiColor = colorMapping.getOrDefault(color, Ansi.Color.DEFAULT);
			while (count > 0) {
				platesSetup += Ansi.ansi().fg(ansiColor).a("(" + plateWeight + ") ").reset();
				count -= 1;
			}
		}

		// reverse platesSetup because bigger plate have to be first
		// split string into an array
		String[] plateArray = platesSetup.split(" ");
		// reverse array
		Collections.reverse(Arrays.asList(plateArray));
		String reversedPlateSetup = String.join(" ", plateArray);

		System.out.println("************************************************************");
		System.out.println("*                 BARBELL LOADING GUIDE                    *");
		System.out.println("************************************************************");
		System.out.println("Target weight: " + targetWeight + "\n\n");
		System.out.println(
				reversedPlateSetup + Ansi.ansi().fgBlue().a("|	BAR 45 lbs	| ").reset() + platesSetup + "\n\n");
	}

}
