package br.univali.simulator.ui;

import java.util.Scanner;
import br.univali.simulator.domain.Simulation;
import br.univali.simulator.domain.simulation.SimulationConfig;

public class Menu {

    public void showMenu() {
        System.out.println("\n\tWelcome to SOS-RS Simulator");
        System.out.println("\t Choose an option");
        System.out.println("\t 1 - Start simulation");
        System.out.println("\t 2 - Exit");
        System.out.print("\t Option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                SimulationConfig config = questionsSimultion();
                Simulation simulation = new Simulation(config);
                simulation.run();
                break;
            case 2:
                System.out.println("\tExiting simulator...");
                System.exit(0);
                break;
            default:
                System.out.println("\tINVALID OPTION!");
                break;

        }
    }

    public SimulationConfig questionsSimultion() {
        SimulationConfig config = new SimulationConfig();

        System.out.print("\n\t Do you want to configure the simulation? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("Y")) {
            System.out.print("\n\treport the initial quantity of stock in the warehouse: ");
            config.setInitialWarehouseStock(scanner.nextInt());

            System.out.print("\n\tReport the quantity of supplies arriving daily: ");
            config.setDailySuppliesArriving(scanner.nextInt());

            System.out.print("\n\tReport the number of trucks arriving daily: ");
            config.setDailyTrucks(scanner.nextInt());

            System.out.print("\n\tReport the truck capacity: ");
            config.setTruckCapacity(scanner.nextFloat());

            System.out.print("\n\tReport the weight of the supplies: ");
            config.setWeightOfSupplies(scanner.nextFloat());

            System.out.print("\n\tReport the quantity of supplies that can be stacked in each pile: ");
            config.setStorageCapacityOfSupplies(scanner.nextInt());

            System.out.print("\n\tReport the number of batteries stored in the warehouse: ");
            config.setAmountOfStoragesOnWarehouse(scanner.nextInt());

            System.out.print("\n\tReport the number of hours to load a truck: ");
            config.setHoursToLoadTruck(scanner.nextInt());

            System.out.print("\n\tReport the start time of the day: ");
            config.setStartHourOfDay(scanner.nextInt());

            System.out.print("\n\tReport the end time of the day: ");
            config.setEndHourOfDay(scanner.nextInt());
        }

        else {
            System.out.println("\n\tDefault configuration used.");
            return config;
        }

        return config;
    }
}
