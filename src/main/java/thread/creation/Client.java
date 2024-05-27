package thread.creation;

public class Client {
    public static void main(String[] args) {
        System.out.println("Main method start | thread name: " + Thread.currentThread().getName());

        // create thread using thread class
        ThreadExample thread1 = new ThreadExample();
        thread1.start();

        // recommended : create thread using runnable interface
        ThreadRunnable threadRunnable = new ThreadRunnable();
        Thread thread = new Thread(threadRunnable);
        thread.start();
        System.out.println("Main method end | thread name: " + Thread.currentThread().getName());
    }
}
