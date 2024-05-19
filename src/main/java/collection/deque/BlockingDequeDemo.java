package collection.deque;

import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The BlockingDeque interface is a part of the Java Collections Framework. It gets its name because it
 * blocks illegal operations such as insertion into a full queue or deletion from an empty queue,
 * all of these properties are inbuilt into the structure of this interface.
 *
 * @link: <a href="https://www.geeksforgeeks.org/blockingdeque-in-java/">...</a>
 */
public class BlockingDequeDemo {
    public static void main(String[] args) {
        int capacity = 3;
        BlockingDeque<Integer> lbdq = new LinkedBlockingDeque<>(capacity);

        lbdq.add(166);
        lbdq.add(246);
        lbdq.add(66);
        lbdq.add(292);
        lbdq.add(98);

        Iterator<Integer> lbdqIter = lbdq.iterator();

        System.out.println("The LinkedBlockingDeque, lbdq contains:");

        for (int i = 0; i < lbdq.size(); i++) {
            System.out.print(lbdqIter.next() + " ");
        }
    }

}
