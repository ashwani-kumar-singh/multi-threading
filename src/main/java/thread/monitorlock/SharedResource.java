package thread.monitorlock;

public class SharedResource {

    public synchronized void task1() {
        try {
            System.out.println("inside task1");
            Thread.sleep(2000);
            System.out.println("task1 completed");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void task2() {
        try {
            System.out.println("task2 before synchronization");
            synchronized (this) {
                System.out.println("task2 completed");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void task3() {
        System.out.println("task 3 completed");
    }


}
