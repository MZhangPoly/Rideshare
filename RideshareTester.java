public class RideshareTester {
    private static int simulateRoad(Road road, int ticks) {
        System.out.println("\n Starting simulation with a " + road.getRoadCharacteristics() + "... \n ");

        for (int i = 0; i < ticks; i++) {
            road.tick();

            System.out.println(" --------------- tick " + i + " --------------- \n");
            System.out.println(road);
        }

        return road.getRevenueOfAllCars();
    }
    public static void main(String[] args) {
        int iterations = 50;

        int totalRev1 = 0;
        int totalRev2 = 0;

        Road road1 = new Road(20, 50, 31);
        Road road2 = new Road(10, 40, 31);

        int ticks = 100;

        for (int i = 0; i < iterations; i++) {
            road1 = new Road(20, 50, 31);
            road2 = new Road(10, 40, 31);
    
            totalRev1 += simulateRoad(road1, ticks);
            totalRev2 += simulateRoad(road2, ticks);
        }

        System.out.println("Average revenue for " + iterations + " iterations for a " + road1.getRoadCharacteristics() + " revenue: " + ((double) totalRev1) / iterations);
        System.out.println("Average revenue for " + iterations + " iterations for a " + road2.getRoadCharacteristics() + " revenue: " + ((double) totalRev2) / iterations);
    }
}
