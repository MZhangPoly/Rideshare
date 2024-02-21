public class Passenger {
    private int position;
    private int destinationStation;

    private Car currentCar;

    public Passenger(int stationCount) {
        destinationStation = (int) (Math.random() * stationCount);
        position = (int) (Math.random() * stationCount);
    }

    public void enterCar(Car car) {
        currentCar = car;
    }

    public int getDestinationStation() { return destinationStation; }

    public int getPosition() { return position; }

    public Car getCar() { return currentCar; }
}
