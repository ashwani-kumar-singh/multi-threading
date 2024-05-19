package collection.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The ConcurrentLinkedQueue class in Java is a part of the Java Collection Framework. It belongs to
 * java.util.concurrent package. It was introduced in JDK 1.5. It is used to implement Queue with the
 * help of LinkedList concurrently. It is an unbounded thread-safe implementation of Queue which inserts
 * elements at the tail of the Queue in a FIFO(first-in-first-out) fashion. It can be used when an
 * unbounded Queue is shared among many threads.
 *
 * @link: <a href="https://www.geeksforgeeks.org/concurrentlinkedqueue-in-java-with-examples/">...</a>
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args)
    {
        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
        clq.add(12);
        clq.add(70);
        clq.add(1009);
        clq.add(475);

        System.out.println("ConcurrentLinkedQueue: " + clq);
        System.out.println("First Element is: " + clq.peek());
        System.out.println("Head Element is: " + clq.poll());
        System.out.println("ConcurrentLinkedQueue: " + clq);
        System.out.println("Size: " + clq.size());
    }

}
