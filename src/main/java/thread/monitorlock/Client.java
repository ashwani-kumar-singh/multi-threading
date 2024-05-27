package thread.monitorlock;

public class Client {
    public static void main(String[] args) {
        System.out.println("Main method start | thread name: " + Thread.currentThread().getName());
        MonitorLockExample monitorLockExample = new MonitorLockExample();

        Thread thread1 = new Thread(() -> monitorLockExample.task1());
        Thread thread2 = new Thread(() -> monitorLockExample.task2());
        Thread thread3 = new Thread(() -> monitorLockExample.task3());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
