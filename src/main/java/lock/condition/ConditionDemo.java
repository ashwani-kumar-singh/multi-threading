package lock.condition;

/**
 * The Condition class provides the ability for a thread to wait for some condition to occur while executing the
 * critical section.
 * <p>
 * This can occur when a thread acquires the access to the critical section but doesn’t have the necessary condition to
 * perform its operation.
 * <a href="https://www.baeldung.com/java-concurrent-locks#working-with-conditions">...</a>
 */
public class ConditionDemo {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread thread = new Thread(resource::popFromStack);
        thread.start();

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.pushToStack(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                resource.pushToStack(i);
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("element removed: " + resource.popFromStack());
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
