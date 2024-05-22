package br.univali.simulator.ui;

import br.univali.simulator.domain.simulation.SimulationConfig;

import java.util.Scanner;

public class ConfigurationMenu {
	private float askForConfig(int step, String message, float defaultValue) {
		Scanner scanner = new Scanner(System.in);

		String formattedStepNumber = String.format("%02d", step);

		System.out.print("\n\t(" + formattedStepNumber + "/10) " + message + " (Leave 0 to use default value) [" + defaultValue + "]: ");
		float value = scanner.nextFloat();

		if (value > 0) {
			return value;
		}

		return defaultValue;
	}

	private int askForConfig(int step, String message, int defaultValue) {
		Scanner scanner = new Scanner(System.in);

		String formattedStepNumber = String.format("%02d", step);

		System.out.print("\n\t(" + formattedStepNumber + "/10) " + message + " (Leave 0 to use default value) [" + defaultValue + "]: ");
		int value = scanner.nextInt();
		if (value > 0) {
			return value;
		}

		return defaultValue;
	}

	public void askForSimulationConfig(SimulationConfig config) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nDo you want to run the simulation with pauses between each day? (Y/N)");
		boolean shouldRunWithPauses = scanner.nextLine().equalsIgnoreCase("Y");
		config.setRunWithPauses(shouldRunWithPauses);

		System.out.print("\nDo you want to configure the simulation? (Y/N)");
		String option = scanner.nextLine();

		if (option.equalsIgnoreCase("Y")) {
			System.out.println("\nSimulation configurations:");

			config.setInitialWarehouseStock(askForConfig(1, "What is the initial quantity of stock in the warehouse", config.getInitialWarehouseStock()));
			config.setDailySuppliesArriving(askForConfig(2, "What is the quantity of supplies arriving daily", config.getDailySuppliesArriving()));
			config.setDailyTrucks(askForConfig(3, "What is the number of trucks arriving daily", config.getDailyTrucks()));
			config.setTruckCapacity(askForConfig(4, "What is the truck capacity", config.getTruckCapacity()));
			config.setWeightOfSupplies(askForConfig(5, "What is the weight of the supplies", config.getWeightOfSupplies()));
			config.setAmountOfStoragesOnWarehouse(askForConfig(6, "What is the number of piles in the warehouse", config.getAmountOfStoragesOnWarehouse()));
			config.setStorageCapacityOfSupplies(askForConfig(7, "What is the quantity of supplies that can be stacked in each pile of warehouse", config.getStorageCapacityOfSupplies()));
			config.setHoursToLoadTruck(askForConfig(8, "What is the number of hours to load a truck", config.getHoursToLoadTruck()));
			config.setStartHourOfDay(askForConfig(9, "What is the start time of the day", config.getStartHourOfDay()));
			config.setEndHourOfDay(askForConfig(10, "What is the end time of the day", config.getEndHourOfDay()));

			return;
		}

		System.out.println("\n\tDefault configuration used.");
	}

}
