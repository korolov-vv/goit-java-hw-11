public class Exercise2 {
    static int lastNumber;
    static int currentNumber = 1;

    public static void setLastNumber(int lastNumber) {
        Exercise2.lastNumber = lastNumber;
    }

    public void buzzFizzChange(int lastNumber) {
        setLastNumber(lastNumber);
        BuzzFizzChanger bfc = new BuzzFizzChanger();

        Thread threadA = new Thread(() -> bfc.fizz(() -> System.out.print("fizz ")));
        Thread threadB = new Thread(() -> bfc.buzz(() -> System.out.print("buzz ")));
        Thread threadC = new Thread(() -> bfc.fizzbuzz(() -> System.out.print("fizzbuzz ")));
        Thread threadD = new Thread(() -> bfc.number(() -> System.out.print(currentNumber + " ")));

        Thread[] threads = new Thread[]{threadA, threadB, threadC, threadD};

        for(Thread thr: threads){
            thr.start();
        }
    }

    static class BuzzFizzChanger {

        public synchronized void fizz(Runnable printFizz) {

            while (currentNumber <= lastNumber) {
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    printFizz.run();
                    currentNumber++;
                    notifyAll();
                } else {
                    myWait();
                }
            }
        }

        public synchronized void buzz(Runnable printBuzz) {

            while (currentNumber <= lastNumber) {
                if (currentNumber % 3 != 0 && currentNumber % 5 == 0) {
                    printBuzz.run();
                    currentNumber++;
                    notifyAll();
                } else {
                    myWait();
                }
            }
        }

        public synchronized void fizzbuzz(Runnable printFizzBuzz) {

            while (currentNumber <= lastNumber) {
                if (currentNumber % 15 == 0) {
                    printFizzBuzz.run();
                    currentNumber++;
                    notifyAll();
                } else {
                    myWait();
                }
            }
        }

        public synchronized void number(Runnable printNumber) {

            while (currentNumber <= lastNumber) {
                if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                    printNumber.run();
                    currentNumber++;
                    notifyAll();
                } else {
                    myWait();
                }
            }
        }

        public void myWait() {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
