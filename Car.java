import java.util.ArrayList;

public class Car {
    private Road road;

    private int revenue;

    private int roadLength;

    private int position;
    private int destinationStation;
    private boolean increasingPos;

    private ArrayList<Passenger> passengers;
    private int maxPassengers;
    
    public Car(Road road) {
        this.road = road;

        revenue = 0;

        roadLength = road.getLength();

        changePosition((int) (Math.random() * roadLength));
        changeDestinationStation((int) (Math.random() * roadLength));
        
        alignIncreasingPosVal();

        passengers = new ArrayList<Passenger>();
        maxPassengers = 3;

        attemptToPickUpPassengers();
    }

    public void onTick() {
        addRevenueForTick();
        move();
    }

    private void move() {
        if (position + getMovement() >= roadLength || position + getMovement() < 0) // zero index
            increasingPos = !increasingPos; // makes car switch direction if it hits the end of the road

        position += getMovement();

        attemptToDropOffPassengers();
        attemptToPickUpPassengers();
    }

    private void attemptToDropOffPassengers() {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getDestinationStation() == position) {                
                road.passengerDroppedOff(passengers.get(i));

                passengers.remove(i).exitCar();
            }
        }
    }

    private void attemptToPickUpPassengers() {
        for (Passenger passenger : road.getPassengers()) {
            if (passenger.getPosition() == position && (getIncreaseNeededToReachDestination(passenger.getDestinationStation()) == increasingPos)) {
                if (passengers.size() < maxPassengers) {
                    passengers.add(passenger);

                    passenger.enterCar(this);

                    road.passengerPickedUp(passenger);
                } 
            }
        }
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

    private void changeDestinationStation(int newPos) {
        position = newPos;

        if (position == destinationStation) 
            getNewDestination();

    }

    private void getNewDestination() {
        destinationStation = (int) (Math.random() * roadLength);
    }

    public int getRevenue() { return revenue; }

    public int getPosition() { return position; }

    public int getDestinationStation() { return destinationStation; }

    public boolean getIncreasingPos() { return increasingPos; }
}