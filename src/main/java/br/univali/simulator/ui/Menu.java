package br.univali.simulator.ui;

import java.util.Scanner;
import br.univali.simulator.domain.Simulation;
import br.univali.simulator.domain.simulation.SimulationConfig;

public class Menu {
    private ConfigurationMenu configurationMenu;

    public Menu() {
        configurationMenu = new ConfigurationMenu();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 2) {
            System.out.println("=---------------------------------=");
            System.out.println("  ___  ___  ___     ___  ___ ");
            System.out.println(" / __|/ _ \\/ __|___| _ \\/ __|");
            System.out.println(" \\__ \\ (_) \\__ \\___|   /\\__ \\");
            System.out.println(" |___/\\___/|___/   |_|_\\|___/ v1.0.0");
            System.out.println(" By: Caio Rosa & Jordan Lippert");
            System.out.println("\nWelcome to SOS-RS Simulator\n");
            System.out.println("Choose an option:\n");
            System.out.println("\t 1 - Start simulation");
            System.out.println("\t 2 - Exit");
            System.out.print("\n\t Option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    SimulationConfig simulationConfig = new SimulationConfig();

                    configurationMenu.askForSimulationConfig(simulationConfig);

                    Simulation simulation = new Simulation(simulationConfig, configurationMenu);

                    simulation.run();
                    break;
                case 2:
                    System.out.println("\tExiting simulator...");
                    System.out.println("=---------------------------------=");
                    break;
                default:
                    System.out.println("\tInvalid option, please try again.");
                    break;
            }
        }
    }
}
