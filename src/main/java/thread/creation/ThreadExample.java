package thread.creation;

public class ThreadExample extends Thread {
    @Override
    public void run() {
        System.out.println("Using Thread Class: Executing run method for thread: " + Thread.currentThread().getName());
    }
}
