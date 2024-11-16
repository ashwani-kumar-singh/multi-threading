package thread.misc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Each thread has it's own thread local object.
 * ThreadLocal provides thread restriction which is an extension of a local variable. ThreadLocal is visible only in
 * a single thread. No two threads can see each other’s thread-local variable.
 * These variables are generally private static fields in classes and maintain their state inside the thread.
 * <p>
 * get() -> Returns the value in the current thread’s copy of this thread-local variable. If the variable has
 * no value for the current thread, it is first initialized to the value returned by an invocation
 * of the initialValue() method
 * <p>
 * initialValue()-> Returns the current thread initial value for the local thread variable.
 * <p>
 * remove()->	Removes the current thread’s value for this thread-local variable. If this thread-local variable
 * is subsequently read by the current thread, its value will be reinitialized by invoking its initialValue() method,
 * unless its value is set by the current thread in the interim. This may result in multiple invocations of the
 * initialValue method in the current thread.
 * <p>
 * set()-> Sets the current thread’s copy of this thread-local variable to the specified value. Most subclasses will
 * have no need to override this method, relying solely on the initialValue() method to set the values of thread locals.
 *
 * <a href="https://www.geeksforgeeks.org/java-lang-threadlocal-class-java/">...</a>
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal() {
            protected String initialValue() {
                return "Initial Value";
            }
        };
        System.out.println("thread initial value: " + threadLocal.get());
        threadLocal.set(Thread.currentThread().getName());

        System.out.println("thread local content: " + threadLocal.get());

        Thread thread = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println("thread local content: " + threadLocal.get());
            // clean the thread local variable when reusing thread
            threadLocal.remove();
        });
        thread.setName("child thread");
        thread.start();

        int noOfThread = 5;
        ExecutorService pool = Executors.newFixedThreadPool(noOfThread);
        pool.submit(() -> {
            threadLocal.set(Thread.currentThread().getName());
            threadLocal.remove();
        });

        for (int i = 0; i < noOfThread; i++) {
            pool.submit(()-> System.out.println(threadLocal.get()));
        }

        pool.shutdown();
    }


}
