package br.univali.simulator.domain;

import br.univali.simulator.domain.entities.Warehouse;
import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.domain.simulation.SimulationLog;
import br.univali.simulator.ui.ConfigurationMenu;
import br.univali.simulator.utils.LinkedList;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class Simulation {
    @Getter
    private final SimulationConfig config;
    private final ConfigurationMenu configurationMenu;

    @Getter
    private final LinkedList<SimulationLog> logs;
    private final Warehouse warehouse;

    private int currentDay;
    private int currentHour;

    public Simulation(SimulationConfig config, ConfigurationMenu configurationMenu) {
        this.config = config;
        this.configurationMenu = configurationMenu;

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
        System.out.println("Initial warehouse weight: " + warehouse.getTotalWeight());
        warehouse.receiveSupplies(config.getDailySuppliesArriving(), config.getWeightOfSupplies());
        System.out.println("Current warehouse weight after supplies arriving: " + warehouse.getTotalWeight());
        warehouse.arriveTrucks(config);

        currentHour = config.getStartHourOfDay();
        while (currentHour < config.getEndHourOfDay()) {
            processHour();
            currentHour++;
        }

        System.out.println("Total weight in warehouse on end of day: " + warehouse.getTotalWeight());
        if (warehouse.getTotalWeight() == 0) {
            System.out.println("\n\tAll the supplies were delivered, finishing operation.");
            return;
        }

        if (config.isRunWithPauses()) {
            configurationMenu.askForSimulationConfig(config);
        }

        System.out.println("\n\tWaiting 5 seconds to start next day...");
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
