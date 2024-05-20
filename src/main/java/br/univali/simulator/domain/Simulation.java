package br.univali.simulator.domain;

import br.univali.simulator.domain.entities.Truck;
import br.univali.simulator.domain.entities.Warehouse;
import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.domain.simulation.SimulationLog;
import br.univali.simulator.utils.LinkedList;


public class Simulation {
    private final SimulationConfig config;
    private final LinkedList<SimulationLog> logs;
    private final Warehouse warehouse;

    private int currentDay;
    private int currentHour;

    public Simulation(SimulationConfig config) {
        this.config = config;
        logs = new LinkedList<>();
        warehouse = new Warehouse();

        currentDay = 0;
        currentHour = config.getStartHourOfDay();

        warehouse.initStorages(config);
    }

    public void processHour() {
        if (!warehouse.hasTrucks()) {
            return;
        }

        Truck truck = warehouse.loadTruck();
    }

    public void processDay() {
        System.out.println("=---------------------------------------=");
        System.out.println("Processing day " + currentDay + "...");
        warehouse.arriveTrucks(config);
    }

    public void run() {
        while (currentDay < 365 || warehouse.isEmpty()) {
            processDay();
            currentDay++;
            while (currentHour < config.getEndHourOfDay()) {
                processHour();
                currentHour++;
            }
        }

        System.out.println("Simulation finished, currentDay: " + currentDay + ", currentHour: " + currentHour);
    }
}
