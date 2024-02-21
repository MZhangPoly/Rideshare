public class Car {
    private int revenue;

    private int destinationStation;
    private boolean increasingPos;

    public Car(int destinationStation, boolean increasingPos) {
        revenue = 0;

        this.destinationStation = destinationStation;
        this.increasingPos = increasingPos;
    }

    public int getRevenue() { return revenue; }

    public int getDestinationStation() { return destinationStation; }

    public boolean getIncreasingPos() { return increasingPos; }
}