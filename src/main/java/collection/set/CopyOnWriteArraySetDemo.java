package collection.set;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The internal implementation of CopyOnWriteArraySet is CopyOnWriteArrayList only.
 * a. Multiple Threads are able to perform update operations simultaneously but for every update operation,
 * a separate cloned copy is created.
 * b. As for every update a new cloned copy will be created which is costly.
 * Hence if multiple update operations are required then it is not recommended to use CopyOnWriteArraySet.
 * c. While one thread iterating the Set, other threads can perform updation, here we won’t get any runtime exception
 * like ConcurrentModificationException.
 * d. An Iterator of CopyOnWriteArraySet class can perform only read-only and should not perform the deletion,
 * otherwise, we will get Run-time exception UnsupportedOperationException.
 * e. Use CopyOnWriteArraySet in applications in which set sizes generally stay small, read-only
 * operations vastly outnumber mutative operations, and you need to prevent interference among threads
 * during traversal.
 * f. CopyOnWriteArraySet helps in minimizing programmer-controlled synchronization steps and
 * moving the control to inbuilt, well-tested APIs
 *
 * @link <a href="https://www.geeksforgeeks.org/copyonwritearrayset-in-java/">...</a>
 */
public class CopyOnWriteArraySetDemo extends Thread {

    static Set<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    public void run() {
        // Child thread trying to add new element in the Set object
        copyOnWriteArraySet.add("D");
    }

    public static void main(String[] args) {
        copyOnWriteArraySet.add("A");
        copyOnWriteArraySet.add("B");
        copyOnWriteArraySet.add("C");

        // We create a child thread that is going to modify CopyOnWriteArraySet l.
        CopyOnWriteArraySetDemo copyOnWriteArraySetDemo = new CopyOnWriteArraySetDemo();
        // Running the child thread using start() method
        copyOnWriteArraySetDemo.start();
        // Waiting for the thread to add the element try block to check for exceptions
        try {

            Thread.sleep(2000);
        }
        // Catch block to handle exceptions
        catch (InterruptedException e) {
            System.out.println("child going to add element");
        }
        System.out.println(copyOnWriteArraySet);
        // Now we iterate through the CopyOnWriteArraySet and we won't get exception.
        Iterator<String> itr = copyOnWriteArraySet.iterator();

        while (itr.hasNext()) {
            String s = itr.next();
            System.out.println(s);

            if (s.equals("C")) {
                // Here we will get RuntimeException
                itr.remove();
            }
        }
    }
}


