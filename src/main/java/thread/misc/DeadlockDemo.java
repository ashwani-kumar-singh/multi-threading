package thread.misc;

/**
 * Deadlock is a situation when two or more threads are waiting for each other and the waiting never ends.
 * Here both threads can’t complete their tasks.
 *
 * <a href="https://www.geeksforgeeks.org/introduction-of-deadlock-in-operating-system/">...</a>
 */
public class DeadlockDemo extends Thread {

    private static Thread mainThread = new Thread(() -> {

    });

    @Override
    public void run() {
        System.out.println("Child Thread waiting for main thread completion");
        try {
            // Child thread waiting for completion of main thread
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockDemo.mainThread = Thread.currentThread();
        DeadlockDemo childThread = new DeadlockDemo();
        childThread.start();
        System.out.println("Main thread waiting for Child thread completion");

        // main thread is waiting for the completion of Child thread
        childThread.join();

        System.out.println("Main thread execution completes");
    }


    static void deadlockExample() {
        Object p = new Object();
        synchronized (p) {
            synchronized (p) {
                // deadlock. Since p is previously lockedwe will never reach here...
            }
        }
    }

}
