public class Car {
    private int revenue;

    private int destinationStation;
    private int position;
    private boolean increasingPos;

    public Car(int stationCount) {
        revenue = 0;

        destinationStation = (int) (Math.random() * stationCount);
        position = (int) (Math.random() * stationCount);
        
        alignIncreasingPosVal();
    }

    private void alignIncreasingPosVal() {
        increasingPos = position < destinationStation;
    }

    public int getRevenue() { return revenue; }

    public int getDestinationStation() { return destinationStation; }

    public boolean getIncreasingPos() { return increasingPos; }
}