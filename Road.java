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

        constructCars();
        constructPassengers();
    }

    public void tick() {
        for (Car car : cars) {
            car.onTick();
        }
    }

    public void passengerPickedUp(Passenger passenger) {
        passengers.remove(passenger);
    }

    public void passengerDroppedOff(Passenger passenger) {
        passengers.add(passenger);
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

    private int getCarCountOnMile(int mile) {
        int carsOnMile = 0;

        for (Car car : cars) 
            if (car.getPosition() == mile)
                carsOnMile++;

        return carsOnMile;
    }

    private int getPassengerCountOnMile(int mile) {
        int passengersOnMile = 0;

        for (Passenger passenger : passengers) 
            if (passenger.getPosition() == mile)
                passengersOnMile++;

        return passengersOnMile;
    }

    private ArrayList<Car> getCarsOnMile(int mile) {
        ArrayList<Car> carsOnMile = new ArrayList<Car>();

        for (Car car : cars)
            if (car.getPosition() == mile)
                carsOnMile.add(car);
        
        return carsOnMile;
    }

    private ArrayList<Passenger> getPassengersOnMile(int mile) {
        ArrayList<Passenger> passengersOnMile = new ArrayList<Passenger>();

        for (Passenger passenger : passengers)
            if (passenger.getPosition() == mile)
                passengersOnMile.add(passenger);
        
        return passengersOnMile;
    }

    public String toString() {
        String str = "";

        for (int i = 0; i < length; i++) {
            str += "Mile " + i + ": cars - " + getCarCountOnMile(i) + " | passengers - " + getPassengerCountOnMile(i) + " ||| ";
            
            for (Car car : getCarsOnMile(i)) {
                str += car;
            }

            for (Passenger passenger : getPassengersOnMile(i)) {
                str += passenger;
            }

            str += "\n";
        }

        return str;
    }
}
