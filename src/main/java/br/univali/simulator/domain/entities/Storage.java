package br.univali.simulator.domain.entities;

public class Storage extends Warehouse {
    private int capacity;

    public Storage(int speed) {
        super(speed);
        this.capacity = 100000;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull(int weight) {
        return weight >= capacity;
    }

    public boolean isEmpty(int weight) {
        return weight == 0;
    }

    public int getRemainingCapacity(int weight) {
        return capacity - weight;
    }

    public int getRemainingCapacity(int weight, int capacity) {
        return capacity - weight;
    }

    public void storageLoad(int weight) {
        if (weight <= capacity) {
            capacity += weight;
        } else {
            throw new IllegalArgumentException("Exceeds storage capacity");
        }
    }

    public void storageUnload(int weight) {
        if (capacity - weight >= 0) {
            capacity -= weight;
        } else {
            throw new IllegalArgumentException("Insufficient load to unload");
        }
    }
}
