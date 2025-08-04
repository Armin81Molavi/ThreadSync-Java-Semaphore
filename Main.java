import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphoreCD = new Semaphore(1);
        Semaphore semaphoreAB = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    semaphoreAB.acquire(); //wait
                    System.out.print('C');
                    System.out.print('D');
                    semaphoreCD.release(); //signal
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    semaphoreCD.acquire(); //wait
                    System.out.print('A');
                    System.out.print('B');
                    semaphoreAB.release(); //signal
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}