package thread.monitorlock;

/**
 * @link <a href="https://www.geeksforgeeks.org/difference-between-lock-and-monitor-in-java-concurrency/">...</a>
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("Main method start | thread name: " + Thread.currentThread().getName());
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(sharedResource::task1);
        Thread thread2 = new Thread(sharedResource::task2);
        Thread thread3 = new Thread(sharedResource::task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
