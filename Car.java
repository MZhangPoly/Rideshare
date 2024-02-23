import java.util.ArrayList;

public class Car {
    private Road road;

    private static int amountOfCars = 0;
    private int uniqueId;

    private int revenue;

    private int roadLength;

    private int position;
    private int destinationStation;
    private boolean increasingPos;

    private ArrayList<Passenger> passengers;
    private int maxPassengers;
    
    public Car(Road road) {
        this.road = road;

        uniqueId = amountOfCars;
        amountOfCars++;

        revenue = 0;

        roadLength = road.getLength();

        changePosition((int) (Math.random() * roadLength));
        getNewDestination();

        alignIncreasingPosVal();

        passengers = new ArrayList<Passenger>();
        maxPassengers = 3;

        attemptToPickUpPassengers();
    }

    public void onTick() {
        move();
    }

    private void move() {
        // if (position + getMovement() >= roadLength || position + getMovement() < 0) { // zero index
        //     increasingPos = !increasingPos; // makes car switch direction if it hits the end of the road
        // }

        position += getMovement(); // must be in else statement or else the car will immediately leave the edge of road position without changing direction there first
        
        for (Passenger passenger : passengers)
            passenger.moveWithCar(getMovement());

        addRevenueForTick();

        if (position == destinationStation)
            getNewDestination();

        attemptToDropOffPassengers();
        attemptToPickUpPassengers();
    }

    private void attemptToDropOffPassengers() {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getDestinationStation() == position) {
                passengers.get(i).exitCar();

                road.passengerDroppedOff(passengers.get(i));

                passengers.remove(i);
                i--;
            }
        }
    }

    private void attemptToPickUpPassengers() {
        for (int i = 0; i < road.getPassengers().size(); i++) {
            Passenger passenger = road.getPassengers().get(i);

            if (passenger.getPosition() == position && (canDrivePassenger(passenger.getDestinationStation()))) {
                if (passengers.size() < maxPassengers) {
                    passengers.add(passenger);

                    passenger.enterCar(this);

                    road.passengerPickedUp(passenger);
                } 
            }
        }
    }

    private boolean canDrivePassenger(int passengerDestination) {
        return carAndPassengerSameDirection(passengerDestination) && Math.abs((destinationStation - position)) >= Math.abs((passengerDestination - position));
    }

    private boolean carAndPassengerSameDirection(int passengerDestination) {
        return increasingPos == getIncreaseNeededToReachDestination(passengerDestination);
    }

    private void addRevenueForTick() {
        revenue += passengers.size();
    }

    private int getMovement() {
        return increasingPos ? 1 : -1;
    }

    private void alignIncreasingPosVal() {
        increasingPos = getIncreaseNeededToReachDestination(destinationStation);
    }

    private boolean getIncreaseNeededToReachDestination(int destination) {
        return position < destination;
    }

    private void changePosition(int newPos) {
        position = newPos;

        if (position == destinationStation)
            getNewDestination();

    }

    // private void changeDestinationStation(int newPos) {
    //     destinationStation = newPos;

    //     if (position == destinationStation) 
    //         getNewDestination();

    // }

    private void getNewDestination() {
        int newDestinationStation = (int) (Math.random() * roadLength);

        while (newDestinationStation == destinationStation || newDestinationStation == position)
            newDestinationStation = (int) (Math.random() * roadLength);

        destinationStation = newDestinationStation;
        alignIncreasingPosVal();
    }

    public int getRevenue() { return revenue; }

    public int getPosition() { return position; }

    public int getDestinationStation() { return destinationStation; }

    public boolean getIncreasingPos() { return increasingPos; }

    private String increasingPosToDirectionString() {
        return increasingPos ? "+" : "-";
    }

    public String toString() {
        String str = "Car " + uniqueId + ": revenue - " + revenue + " | direction: (" + increasingPosToDirectionString() + ") | destination: " + destinationStation + " | passengers: ";
       // String str = "Car " + uniqueId + " | destination: " + destinationStation + " | passengers: ";

        if (passengers.size() > 0)
            str += " {";

        for (Passenger passenger : passengers) {
            str += passenger;
        }

        if (passengers.size() == 0)
            str += " None ";
        else
            str += "} ";

        return str;
    }
}