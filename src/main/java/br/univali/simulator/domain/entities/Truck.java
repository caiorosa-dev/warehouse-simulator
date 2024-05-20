package br.univali.simulator.domain.entities;

import br.univali.simulator.utils.DynamicStack;
import br.univali.simulator.utils.truck.LicensePlateGenerator;

public class Truck {
	private final DynamicStack<Supply> loadedSupplies;
	private final float capacity;
	private final String id;

	public Truck(float capacity) {
		loadedSupplies = new DynamicStack<>();
		this.capacity = capacity;
		this.id = LicensePlateGenerator.generate();
	}

	public float calculateTotalWeight() {
		final float[] totalWeight = {0};

		loadedSupplies.forEach(supply -> {
			totalWeight[0] += supply.getWeight();
		});

		return totalWeight[0];
	}

	public boolean isFull() {
		return this.calculateTotalWeight() >= capacity;
	}

	public boolean load(Supply supply) {
		if (this.calculateTotalWeight() > capacity) {
			return false;
		}

		loadedSupplies.push(supply);
		return true;
	}

	public String getMessage() {
		return "Truck " + id + " loaded with " + loadedSupplies.size() + " supplies.";
	}
}
