package br.univali.simulator.domain.entities;

public class Warehouse {

    private int capacity; //TODO: capacidade 100.000kg (simulação inicial)
    private int speed;

    public Warehouse(int speed) {
        this.capacity = 100000;
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    Truck [] trucks = new Truck[10]; //TODO: 10 caminhões (simulação inicial)
}