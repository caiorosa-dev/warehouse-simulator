package br.univali.simulator.domain.entities;

import br.univali.simulator.utils.DynamicStack;
import lombok.Data;

@Data
public class Storage {
	private DynamicStack<Supply> supplies;
	private int maxSize;

	public Storage(int maxSize) {
		supplies = new DynamicStack<>();
		this.maxSize = maxSize;
	}

	public Supply unloadSupply() {
		Supply supply = supplies.pop();

		return supply;
	}

	public boolean hasSupplies() {
		return !supplies.isEmpty();
	}

	public boolean canLoadSupply() {
		return supplies.size() < maxSize;
	}

	public boolean loadSupply(Supply supply) {
		if (!canLoadSupply()) {
			return false;
		}

		supplies.push(supply);
		return true;
	}
}
