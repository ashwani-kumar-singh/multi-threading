package collection.queue;

/**
 *
 *The PriorityBlockingQueue is an unbounded blocking queue that uses the same ordering rules as class
 * PriorityQueue and supplies blocking retrieval operations. Since it is unbounded, adding elements may
 * sometimes fail due to resource exhaustion resulting in OutOfMemoryError. This class does not permit
 * null elements.
 * The iterator() method of PriorityBlockingQueue class Returns an iterator over the elements in
 * this queue.
 * The elements returned from this method do not follow any order.
 *
 * @link: https://www.geeksforgeeks.org/priorityblockingqueue-class-in-java/
 *
 */

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {
    public static void main(String[] args)
    {
        int capacity = 15;
        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(capacity);

        pbq.add(1);
        System.out.println("PriorityBlockingQueue:" + pbq);
        pbq.add(5);
        System.out.println("PriorityBlockingQueue:" + pbq);
        pbq.add(3);
        System.out.println("PriorityBlockingQueue:" + pbq);
        pbq.add(8);
        System.out.println("PriorityBlockingQueue:" + pbq);
        pbq.add(2);
        System.out.println("PriorityBlockingQueue:" + pbq);
        pbq.add(4);

        // print queue
        System.out.println("PriorityBlockingQueue:" + pbq);

        // create object of PriorityBlockingQueue
        PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>(capacity, Comparator.reverseOrder());

        // add  numbers
        blockingQueue.add(1);
        System.out.println("PriorityBlockingQueue:" + blockingQueue);
        blockingQueue.add(2);
        System.out.println("PriorityBlockingQueue:" + blockingQueue);
        blockingQueue.add(3);

        // print queue
        System.out.println("PriorityBlockingQueue:" + blockingQueue);
    }
}
