public class Passenger {
    private static int passengersCreated = 0;
    private int uniqueId;

    private int position;
    private int destinationStation;

    private int roadLength;

    private Car currentCar;

    public Passenger(int roadLength) {
        uniqueId = passengersCreated;
        passengersCreated++;

        changePosition((int) (Math.random() * roadLength));
        changeDestinationStation((int) (Math.random() * roadLength));

        this.roadLength = roadLength;

        currentCar = null;
    }

    public void enterCar(Car car) {
        currentCar = car;
    }

    public void exitCar() {
        currentCar = null;
    }

    private void changePosition(int newPos) {
        position = newPos;

        if (position == destinationStation)
            getNewDestination();

    }

    private void changeDestinationStation(int newPos) {
        position = newPos;

        if (position == destinationStation) 
            getNewDestination();

    }

    private void getNewDestination() {
        destinationStation = (int) (Math.random() * roadLength);
    }

    public int getDestinationStation() { return destinationStation; }

    public int getPosition() { return position; }

    public Car getCar() { return currentCar; }

    public String toString() {
        return "Passenger " + uniqueId + ": desired dstination - " + destinationStation;
    }
}
