package br.univali.simulator.domain.entities;

import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.utils.DynamicQueue;
import br.univali.simulator.utils.LinkedList;

public class Warehouse {
	private final DynamicQueue<Truck> trucks;
	private final LinkedList<Storage> storages;
	private final DynamicQueue<Supply> suppliesOnHold;

	public Warehouse() {
		trucks = new DynamicQueue<>();
		storages = new LinkedList<>();
		suppliesOnHold = new DynamicQueue<>();
	}

	public float getTotalWeight() {
		float totalWeight = 0;

		for (int i = 0; i < storages.size(); i++) {
			totalWeight += storages.get(i).getTotalWeight();
		}

		return totalWeight;
	}

	public void initStorages(SimulationConfig config) {
		int suppliesToBeCreated = config.getInitialWarehouseStock();

		for (int i = 0; i < config.getAmountOfStoragesOnWarehouse(); i++) {
			Storage storage = new Storage(config.getStorageCapacityOfSupplies());

			while (storage.canLoadSupply() && suppliesToBeCreated > 0) {
				Supply supply = new Supply(config.getWeightOfSupplies());

				storage.loadSupply(supply);

				suppliesToBeCreated--;
			}

			storages.add(storage);
		}

		while (suppliesToBeCreated > 0) {
			Supply supply = new Supply(config.getWeightOfSupplies());

			suppliesOnHold.enqueue(supply);
			suppliesToBeCreated--;
		}

		storages.forEach((storage -> {
			System.out.println("Storage with " + storage.getSupplies().size() + " supplies.");
		}));
		System.out.println("Warehouse initialized with " + storages.size() + " storages.");

	}

	public void arriveTrucks(SimulationConfig config) {
		for (int i = 0; i < config.getDailyTrucks(); i++) {
			Truck truckToBeEnqueued = new Truck(config.getTruckCapacity());

			trucks.enqueue(truckToBeEnqueued);
		}
	}

	public Truck loadTruck() {
		Truck currentTruck = trucks.dequeue();

		if (currentTruck == null) {
			return null;
		}

		storages.forEach((storage -> {
			while (!currentTruck.isFull() && storage.hasSupplies()) {
				Supply supply = storage.unloadSupply();

				currentTruck.load(supply);
			}
		}));


		System.out.println("Truck loaded with " + currentTruck.calculateTotalWeight() + "kg of supplies.");

		return currentTruck;
	}

	public boolean hasTrucks() {
		return trucks.size() != 0;
	}

	public boolean isEmpty() {
		for (int i = 0; i < storages.size(); i++) {
			if (storages.get(i).hasSupplies()) {
				return false;
			}
		}

		return trucks.isEmpty() && suppliesOnHold.isEmpty();
	}
}
