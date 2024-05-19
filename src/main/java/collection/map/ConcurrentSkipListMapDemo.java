package collection.map;

import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * The ConcurrentSkipListMap is a scalable implementation of ConcurrentNavigableMap. All the elements
 * are sorted based on natural ordering or by the Comparator passed during it’s construction time.
 * This class uses a concurrent variation of SkipList data structure providing log(n) time cost for
 * insertion, removal, update, and access operations.
 * These operations are safe for executing concurrently by multiple threads.
 *
 * @link <a href="https://www.geeksforgeeks.org/concurrentskiplistmap-in-java-with-examples/">...</a>
 */
public class ConcurrentSkipListMapDemo {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String, String> cslm = new ConcurrentSkipListMap<>();

        cslm.put("3", "Geeks");
        cslm.put("2", "from");
        cslm.put("1", "Hi!");
        cslm.put("5", "Geeks");
        cslm.put("4", "for");

        System.out.println("Initial Map : " + cslm);

        // print key-value pair whose key is greater than 2
        System.out.println("ceilingEntry-2: " + cslm.ceilingEntry("2"));

        // get the descending key set
        NavigableSet<String> navigableSet = cslm.descendingKeySet();
        System.out.println("descendingKeySet: ");

        for (String s : navigableSet) {
            System.out.println(s);
        }

        // print the first mapping
        System.out.println("firstEntry: " + cslm.firstEntry());

        // print the last mapping
        System.out.println("lastEntry: " + cslm.lastEntry());

        // remove the first mapping and print it
        System.out.println("pollFirstEntry: " + cslm.pollFirstEntry());

        // print the first mapping
        System.out.println("now firstEntry: " + cslm.firstEntry());

        // remove the last mapping and print it
        System.out.println("pollLastEntry: " + cslm.pollLastEntry());

        // print the last mapping
        System.out.println("now lastEntry: "
                + cslm.lastEntry());
    }
}
