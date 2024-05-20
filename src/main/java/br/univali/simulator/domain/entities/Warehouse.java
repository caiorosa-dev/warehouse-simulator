package br.univali.simulator.domain.entities;

import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.utils.DynamicQueue;
import br.univali.simulator.utils.LinkedList;
import lombok.var;

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

		System.out.println("Arrived " + config.getDailyTrucks() + " trucks.");
	}

	private void moveSuppliesOnHoldToStorages() {
		for (int i = 0; i < storages.size(); i++) {
			Storage currentStorage = storages.get(i);

			if (!currentStorage.canLoadSupply()) {
				continue;
			}

			while (currentStorage.canLoadSupply() && !suppliesOnHold.isEmpty()) {
				Supply supply = suppliesOnHold.dequeue();

				currentStorage.loadSupply(supply);
			}
		}
	}

	public void receiveSupplies(int amount, float weightOfSupplies) {
		for (int i = 0; i < amount; i++) {
			var supply = new Supply(weightOfSupplies);

			suppliesOnHold.enqueue(supply);
		}

		this.moveSuppliesOnHoldToStorages();
		System.out.println("Received " + amount + " supplies.");
	}

	public void loadTruck() {
		Truck currentTruck = trucks.dequeue();

		if (currentTruck == null || !hasSupplies()) {
			return;
		}

		storages.forEach((storage -> {
			while (!currentTruck.isFull() && storage.hasSupplies()) {
				Supply supply = storage.unloadSupply();

				currentTruck.load(supply);
			}
		}));

		if (suppliesOnHold.size() > 0) {
			this.moveSuppliesOnHoldToStorages();
		}

		System.out.println(currentTruck.getMessage());
	}

	public boolean hasTrucks() {
		return trucks.size() != 0;
	}

	public boolean hasSupplies() {
		for (int i = 0; i < storages.size(); i++) {
			if (storages.get(i).hasSupplies()) {
				return true;
			}
		}

		return false;
	}

	public boolean isEmpty() {
		if (hasSupplies()) {
			return false;
		}

		return trucks.isEmpty() && suppliesOnHold.isEmpty();
	}
}
