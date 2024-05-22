package br.univali.simulator.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class SimulationConfig {
    // Simulation parameters in KG
    private int initialWarehouseStock = 5000;
    private int dailySuppliesArriving = 250;

    private int dailyTrucks = 10;

    private float truckCapacity = 2000.0f;
    private float weightOfSupplies = 20.0f;

    private int amountOfStoragesOnWarehouse = 50;
    private int storageCapacityOfSupplies = 100;

    private int hoursToLoadTruck = 1;

    private int startHourOfDay = 8;
    private int endHourOfDay = 20;

    private boolean runWithPauses = false;
}
