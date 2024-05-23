package collection.queue;

/**
 * The PriorityBlockingQueue is an unbounded blocking queue that uses the same ordering rules as class
 * PriorityQueue and supplies blocking retrieval operations. Since it is unbounded, adding elements may
 * sometimes fail due to resource exhaustion resulting in OutOfMemoryError. This class does not permit
 * null elements.
 * The iterator() method of PriorityBlockingQueue class Returns an iterator over the elements in
 * this queue.
 * The elements returned from this method do not follow any order.
 *
 * @link: https://www.geeksforgeeks.org/priorityblockingqueue-class-in-java/
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
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


        //The PriorityBlockingQueue implements the BlockingQueue interface, which gives us some extra methods
        // that allow us to block when removing from an empty queue.
        // Let’s try using the take() method, which should do exactly that:

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        Thread thread = new Thread(() -> {
            System.out.println("Polling...");
            while (true) {
                try {
                    Integer poll = queue.take();
                    System.out.println("Polled: " + poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        System.out.println("Adding to queue");

        queue.addAll(Arrays.asList(1, 5, 6, 1, 2, 6, 7));
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        //Thread.interrupt();
    }
}
