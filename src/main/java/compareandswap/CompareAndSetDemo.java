package compareandswap;

/**
 * Compare and swap is a technique used when designing concurrent algorithms.  The approach is to compare the actual
 * value of the variable to the expected value of the variable and if the actual value matches the expected value,
 * then swap the actual value of the variable for the new value passed in.
 *
 * For understanding the algorithm, one must have the basic knowledge of Concurrency and Multithread in Java.
 *
 * Working of the Algorithm:
 *
 * It is like we know that this variable should be 1, and we want to change it to 2. Since this is a multithreaded
 * environment, we know that others might be working on the same variable. So we should first check if the value
 * of the variable is 1 as we thought and if yes, then we change it to 2. If we see that the variable is 3 now,
 * then that means someone else is working on it and so let us not touch it at this time.
 *
 * <a href="https://www.geeksforgeeks.org/java-program-to-implement-cas-compare-and-swap-algorithm/">...</a>
 * <a href="https://www.baeldung.com/java-volatile-vs-atomic">...</a>
 */

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetDemo {
    public static void main(String [] args)
    {
        // Initially value as 0
        AtomicInteger val = new AtomicInteger(0);

        // Prints the updated value
        System.out.println("Previous value: " + val);

        // Checks if previous value was 0 and then updates it
        boolean res = val.compareAndSet(0, 6);

        // Checks if the value was updated.
        if (res)
            System.out.println("The value was"
                    + " updated and it is "
                    + val);
        else
            System.out.println("The value was "
                    + "not updated");
    }
}

