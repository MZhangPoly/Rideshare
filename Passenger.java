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
        
        this.roadLength = roadLength;

        currentCar = null;

        changePosition((int) (Math.random() * roadLength));
        getNewDestination();
    }

    public void enterCar(Car car) {
        currentCar = car;
    }

    public void exitCar() {
        currentCar = null;

        getNewDestination();
    }

    public void moveWithCar(int movement) {
        position += movement;
    }

    private void changePosition(int newPos) {
        position = newPos;

        if (position == destinationStation)
            getNewDestination();

    }

    private void getNewDestination() {
        int newDestination = (int) (Math.random() * roadLength);

        while (newDestination == destinationStation) {
            newDestination = (int) (Math.random() * roadLength);
        }

        destinationStation = newDestination;
    }

    public int getDestinationStation() { return destinationStation; }

    public int getPosition() { return position; }

    public Car getCar() { return currentCar; }

    public String toString() {
        return "Passenger " + uniqueId + ": desired destination - " + destinationStation + " ";
    }
}
