package collection.array;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList creates a Cloned copy of underlying ArrayList, for every update operation
 * at certain point both will synchronized automatically which is takes care by JVM.
 *
 *Therefore, there is no effect for threads which are performing read operation. Therefore,
 * thread-safety is not there in ArrayList whereas CopyOnWriteArrayList is thread-safe.
 * While Iterating ArrayList object by one thread if other thread try to do modification
 * then we will get Runt-time exception saying ConcurrentModificationException. Whereas, We
 * won’t get any Exception in the case of CopyOnWriteArrayList.
 *
 * Iterator of ArrayList can perform remove operation while iteration. But Iterator of
 * CopyOnWriteArrayList cant perform remove operation while iteration, otherwise it will throw
 * run-time exception UnsupportedOperationException.
 */

public class CopyOnWriteArrayListDemo extends Thread {

    static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public void run() {
        // Child thread trying to add new element in the Collection object
        list.add("D");
    }

    public static void main(String[] args)
            throws InterruptedException {
        list.add("A");
        list.add("B");
        list.add("c");

        // We create a child thread that is going to modify ArrayList list.
        CopyOnWriteArrayListDemo copyOnWriteArrayListDemo = new CopyOnWriteArrayListDemo();
        copyOnWriteArrayListDemo.start();

        Thread.sleep(1000);

        for (String s : list) {
            System.out.println(s);
            Thread.sleep(1000);
        }
        System.out.println(list);

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String s = itr.next();
            System.out.println(s);
            if (s.equals("B")) {
                // iterator will throw RuntimeException for remove method while iterating
                itr.remove();
            }
            Thread.sleep(1000);
        }
        System.out.println(list);
    }
}

