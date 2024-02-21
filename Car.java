public class Car {
    private int revenue;

    private int position;
    private int destinationStation;
    private boolean increasingPos;

    private int roadLength;

    public Car(int stationCount) {
        revenue = 0;

        changePosition((int) (Math.random() * stationCount));
        changeDestinationStation((int) (Math.random() * stationCount));
        
        alignIncreasingPosVal();

        roadLength = stationCount;
    }

    private void alignIncreasingPosVal() {
        increasingPos = position < destinationStation;
    }

    public void changePosition(int newPos) {
        position = newPos;

        if (position == destinationStation)
            getNewDestination();

    }

    public void changeDestinationStation(int newPos) {
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