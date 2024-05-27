package thread.creation;

public class ThreadRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Using Runnable Interface: Executing run method for thread: " + Thread.currentThread().getName());
    }
}
