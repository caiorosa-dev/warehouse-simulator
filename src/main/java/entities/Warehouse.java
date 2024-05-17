package entities;

public class Warehouse {

    private int capacity; //TODO: capacidade 100.000kg (simulação inicial)
    private int speed;

    public Warehouse(int capacity, int speed) {
        this.capacity = capacity;
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }
}