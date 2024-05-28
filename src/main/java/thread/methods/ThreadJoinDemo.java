package thread.methods;

/**
 * Thread Join: main thread will wait for thread1 & thread2 finish its execution.
 * It helps in coordination between the thread
 */
public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Executed by thread: " + Thread.currentThread().getName()));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
