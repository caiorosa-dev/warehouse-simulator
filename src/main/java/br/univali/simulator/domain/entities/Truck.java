package br.univali.simulator.domain.entities;

public class Truck {
    private int capacityTruck;  // Capacidade máxima de carga do caminhão
    private int speed;          // Velocidade do caminhão
    private int weight;         // Peso total do caminhão vazio
    private int currentLoad;    // Peso atual da carga no caminhão

    public Truck() {
        this.capacityTruck = 2000;  // Capacidade máxima de carga em kg
        this.speed = 80;            // Velocidade do caminhão em km/h
        this.weight = 2500;         // Peso do caminhão vazio em kg
        this.currentLoad = 0;       // Inicialmente o caminhão está vazio
    }

    public int getCapacityTruck() {
        return capacityTruck;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeight() {
        return weight;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCapacityTruck(int capacityTruck) {
        this.capacityTruck = capacityTruck;
    }

    public boolean isFull() {
        return currentLoad == capacityTruck;
    }

    public boolean isEmpty() {
        return currentLoad == 0;
    }

    public int getRemainingCapacity() {
        return capacityTruck - currentLoad;
    }

    public void load(int weight) {
        if (currentLoad + weight <= capacityTruck) {
            currentLoad += weight;
        } else {
            throw new IllegalArgumentException("Exceeds truck capacity");
        }
    }

    public void unload(int weight) {
        if (currentLoad - weight >= 0) {
            currentLoad -= weight;
        } else {
            throw new IllegalArgumentException("Insufficient load to unload");
        }
    }
}
