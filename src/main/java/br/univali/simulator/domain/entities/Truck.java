package br.univali.simulator.domain.entities;

import br.univali.simulator.utils.DynamicStack;

public class Truck {
	private final DynamicStack<Supply> loadedSupplies;
	private final float capacity;

	public Truck(float capacity) {
		loadedSupplies = new DynamicStack<>();
		this.capacity = capacity;
	}

	public float calculateTotalWeight() {
		final float[] totalWeight = {0};

		loadedSupplies.forEach(supply -> {
			totalWeight[0] += supply.getWeight();
		});

		return totalWeight[0];
	}

	public boolean canLoad(Supply supply) {
		return (supply.getWeight() + calculateTotalWeight()) <= capacity;
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
}
