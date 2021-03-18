public class Exercise1 {
    static final Object MONITOR = new Object();
    static int secondsFromStart = 0;

    public void secondsHandler(int timeInSec) {
        Thread thr1 = new Thread(() -> {

            makePause(1000);
            while (secondsFromStart < timeInSec) {
                synchronized (MONITOR) {
                    secondsFromStart++;
                    if (secondsFromStart % 5 != 0) {
                        System.out.printf("%d seconds have passed from start of the session\n", secondsFromStart);
                    }else {
                        MONITOR.notifyAll();
                        monitorWait();
                    }
                }
                makePause(1000);
            }
        });

        Thread thr2 = new Thread(() -> {
            while (thr1.isAlive()) {
                synchronized (MONITOR) {
                    if (secondsFromStart == 0){
                        System.out.println("Session is started!!!");
                        MONITOR.notifyAll();
                        monitorWait();
                    }else
                    if (secondsFromStart % 5 == 0 && secondsFromStart != timeInSec) {
                        System.out.println("5 seconds have passed");
                        MONITOR.notifyAll();
                        monitorWait();
                    }else if(secondsFromStart == timeInSec){
                        MONITOR.notifyAll();
                    }
                }
            }
        });

        thr1.start();
        thr2.start();
    }


    public static void makePause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void monitorWait() {
        try {
            MONITOR.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}