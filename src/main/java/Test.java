public class Test {
    public static void main(String[] args) {

        new Exercise2().buzzFizzChange(21);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("\n");
        new Exercise1().secondsHandler(11);
    }
}
