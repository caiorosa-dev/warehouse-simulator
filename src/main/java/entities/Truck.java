package entities;

public class Truck {
    private int capacity;
    private int speed;

    public Truck(int capacity, int speed) {
        this.capacity = 2000; //TODO: capacidade 2000kg (capacidade do caminhão)
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }
}
