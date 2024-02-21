public class RideshareTester {
    public static void main(String[] args) {
        int ticks = 50;

        Road road = new Road(2, 3, 5);

        System.out.println("\n Starting simulation... \n ");

        for (int i = 0; i < ticks; i++) {
            road.tick();

            System.out.println(" --------------- tick " + i + " --------------- \n");
            System.out.println(road);
        }
    }
}
