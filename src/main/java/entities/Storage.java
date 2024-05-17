package entities;

public class Storage {
    private int capacity;
    private int speed;

    public Storage(int capacity, int speed) {
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
