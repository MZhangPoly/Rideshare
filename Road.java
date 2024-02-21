import java.util.ArrayList;

public class Road {
    private int carCount;
    private int passengerCount;
    private int length;

    private int ticks;

    private ArrayList<Car> cars;
    private ArrayList<Passenger> passengers;

    public Road(int carCount, int passengerCount, int length) {
        this.carCount = carCount;
        this.passengerCount = passengerCount;
        this.length = length;

        ticks = 0;

        cars = new ArrayList<Car>();
        passengers = new ArrayList<Passenger>();
    }

    public void tick() {
        for (Car car : cars) {
            car.onTick();
        }
    }

    private void constructCars() {
        for (int i = 0; i < carCount; i++) {
            Car car = new Car(this);

            cars.add(car);
        }
    }

    private void constructPassengers() {
        for (int i = 0; i < passengerCount; i++) {
            Passenger passenger = new Passenger(length);

            passengers.add(passenger);
        }
    }

    public int getTicks() { return ticks; }

    public int getLength() { return length; }

    public ArrayList<Passenger> getPassengers() { return passengers; }

    private int getCarsOnMile(int mile) {
        int carsOnMile = 0;

        for (Car car : cars) 
            if (car.getPosition() == mile)
                carsOnMile++;

        return carsOnMile;
    }

    private int getPassengersOnMile(int mile) {
        int passengersOnMile = 0;

        for (Passenger passenger : passengers) 
            if (passenger.getPosition() == mile)
                passengersOnMile++;

        return passengersOnMile;
    }

    public String toString() {
        String str = "";

        for (int i = 0; i < length; i++) {
            str += "Mile " + i + ": cars - " + getCarsOnMile(i) + " | passengers - " + getPassengersOnMile(i) + "\n";
        }

        return str;
    }
}
