import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int MAX_SIZE = 100;

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Random random = new Random();

        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                while (queue.size() < MAX_SIZE) {
                    synchronized (System.out) {
                        System.out.println("Producer produces");
                    }
                    queue.add(random.nextInt());
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                while (queue.size() > 0) {
                    synchronized (System.out) {
                        System.out.println("Consumer consumes");
                    }
                    queue.remove();
                }
            }
        });

        producer.start();
        consumer.start();
        Thread.sleep(30000);
        producer.interrupt();
        consumer.interrupt();
        producer.join();
        consumer.join();
    }
}