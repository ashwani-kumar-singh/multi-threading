package collection.deque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * The ConcurrentLinkedDeque class in Java is a thread-safe implementation of the Deque interface that
 * uses a linked list to store its elements. The ConcurrentLinkedDeque class provides a scalable and
 * high-performance alternative to the ArrayDeque class, particularly in scenarios where multiple
 * threads access the deque concurrently.
 * <p>
 * The ConcurrentLinkedDeque class provides methods for inserting and removing elements from both ends
 * of the deque, and for retrieving elements from the head and tail of the deque, making it a good
 * choice for scenarios where you need to perform many add and remove operations.
 * <p>
 * Disadvantages of using ConcurrentLinkedDeque:
 * More memory overhead: The ConcurrentLinkedDeque class uses a linked list to store its elements, which means
 * that it requires more memory overhead than an array-based implementation, such as the ArrayDeque class.
 * Limited capacity: The ConcurrentLinkedDeque class does not have a limited capacity, but it still
 * requires memory for storing its elements, which means that you may need to create a new
 * ConcurrentLinkedDeque when the old one consumes too much memory.
 *
 * @link <a href="https://www.geeksforgeeks.org/concurrentlinkeddeque-in-java-with-examples/">...</a>
 */
public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<Integer> cld = new ConcurrentLinkedDeque<>();

        cld.addFirst(12);
        cld.addFirst(70);
        cld.addFirst(1009);
        cld.addFirst(475);

        System.out.println("ConcurrentLinkedDeque: " + cld);
        System.out.println("The Last element is: " + cld.getLast());
        System.out.println("First Element is: " + cld.peekFirst());

        cld.removeLast();

        System.out.println("ConcurrentLinkedDeque: " + cld);
    }

}


