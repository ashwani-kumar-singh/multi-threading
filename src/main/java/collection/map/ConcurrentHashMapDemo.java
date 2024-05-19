package collection.map;

/**
 * ConcurrentHashMap is a thread-safe implementation of the Map interface in Java, which means multiple threads can
 * access it simultaneously without any synchronization issues.
 * <p>
 * One of the key features of the ConcurrentHashMap is that it provides fine-grained locking, meaning that it
 * locks only the portion of the map being modified, rather than the entire map. This makes it highly
 * scalable and efficient for concurrent operations.
 * <p>
 * a. The underlined data structure for ConcurrentHashMap is Hashtable.
 * b. ConcurrentHashMap class is thread-safe i.e. multiple threads can operate on a single object without any complications.
 * c. At a time any number of threads are applicable for a read operation without locking the ConcurrentHashMap object
 * which is not there in HashMap.
 * d. In ConcurrentHashMap, the Object is divided into a number of segments according to the concurrency level.
 * e. The default concurrency-level of ConcurrentHashMap is 16.
 * f. In ConcurrentHashMap, at a time any number of threads can perform retrieval operation but for updated in
 * the object, the thread must lock the particular segment in which the thread wants to operate.
 * This type of locking mechanism is known as Segment locking or bucket locking. Hence at a time, 16 update
 * operations can be performed by threads.
 * g. Inserting null objects is not possible in ConcurrentHashMap as a key or value.
 * h. ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another
 * is iterating over it.
 *
 * @link: <a href="https://www.geeksforgeeks.org/concurrenthashmap-in-java/">...</a>
 */

import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> m = new ConcurrentHashMap<>();
        m.put(100, "Hello");
        m.put(101, "Geeks");
        m.put(102, "Geeks");

        // Here we cant add Hello because 101 key is already present in ConcurrentHashMap object
        m.putIfAbsent(101, "Hello");

        // We can remove entry because 101 key  is associated with For value
        m.remove(101, "Geeks");

        // Now we can add Hello
        m.putIfAbsent(103, "Hello");

        // We cant replace Hello with For
        m.replace(101, "Hello", "For");
        System.out.println(m);
    }
}

