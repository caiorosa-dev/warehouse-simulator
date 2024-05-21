package br.univali.simulator.domain;

import br.univali.simulator.domain.entities.Warehouse;
import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.domain.simulation.SimulationLog;
import br.univali.simulator.utils.LinkedList;
import lombok.Getter;

public class Simulation {
    @Getter
    private final SimulationConfig config;

    @Getter
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

        warehouse.loadTruck();
    }

    public void processDay() {
        System.out.println("=---------------------------------------=");
        System.out.println("Processing day " + currentDay + "...");
        warehouse.receiveSupplies(config.getDailySuppliesArriving(), config.getWeightOfSupplies());
        warehouse.arriveTrucks(config);

        currentHour = config.getStartHourOfDay();
        while (currentHour < config.getEndHourOfDay()) {
            processHour();
            currentHour++;
        }

        System.out.println("Total weight in warehouse: " + warehouse.getTotalWeight());
        if (warehouse.getTotalWeight() == 0) {
            System.out.println("All the supplies were delivered, finishing operation.");
        }
    }

    public void run() {
        while (currentDay < 365 && !warehouse.isEmpty()) {
            currentDay++;
            processDay();
        }

        System.out.println("=---------------------------------------=");
        System.out.println("Simulation finished, end day: " + currentDay);
    }
}
