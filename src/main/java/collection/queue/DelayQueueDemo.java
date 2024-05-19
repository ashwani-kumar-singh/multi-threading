package collection.queue;

/**
 * The DelayQueue class is a member of the Java Collections Framework. It belongs to java.util.concurrent package.
 * DelayQueue implements the BlockingQueue interface. DelayQueue is a specialized Priority Queue that orders
 * elements based on their delay time. It means that only those elements can be taken from the queue whose
 * time has expired.
 * DelayQueue head contains the element that has expired in the least time. If no delay has expired,
 * then there is no head and the poll will return null. DelayQueue accepts only those elements that
 * belong to a class of type Delayed or those implement java.util.concurrent.Delayed interface.
 * The DelayQueue blocks the elements internally until a certain delay has expired.
 *
 * @link: https://www.geeksforgeeks.org/delayqueue-class-in-java-with-example/
 *
 */

import java.util.concurrent.*;

// The DelayObject for DelayQueue It must implement Delayed and its getDelay() and compareTo() method
class DelayObject implements Delayed {

    private final String name;
    private final long time;

    public DelayObject(String name, long delayTime) {
        this.name = name;
        this.time = System.currentTimeMillis()
                + delayTime;
    }

    // Implementing getDelay() method of Delayed
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    // Implementing compareTo() method of Delayed
    @Override
    public int compareTo(Delayed obj) {
        return Long.compare(this.time, ((DelayObject) obj).time);
    }

    @Override
    public String toString() {
        return "\n{"
                + "name=" + name
                + ", time=" + time
                + "}";
    }
}

// Driver Class
public class DelayQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<DelayObject> DQ = new DelayQueue<>();

        DQ.add(new DelayObject("A", 1));
        DQ.add(new DelayObject("B", 2));
        DQ.add(new DelayObject("C", 3));
        DQ.add(new DelayObject("D", 4));

        System.out.println("DelayQueue: " + DQ);

        // print the head using peek() method
        System.out.println("Head of DelayQueue: " + DQ.peek());

        System.out.println("Size of DelayQueue: " + DQ.size());

        System.out.println("Head of DelayQueue: " + DQ.poll());

        System.out.println("Size of DelayQueue: " + DQ.size());

        // clear the DelayQueue using clear() method
        DQ.clear();
        System.out.println("Size of DelayQueue" + " after clear: " + DQ.size());
    }
}

