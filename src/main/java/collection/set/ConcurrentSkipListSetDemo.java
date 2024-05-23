package collection.set;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * The implementation of ConcurrentSkipListSet is based on ConcurrentSkipListMap. The elements in ConcurrentSkipListSet
 * are sorted by default in their natural ordering or by a Comparator provided at set creation time, depending on
 * which constructor is used.
 * The elements in ConcurrentSkipListSet are sorted by default in their natural ordering or by a Comparator
 * provided at set creation time, depending on which constructor is used.
 * a. It implements Serializable, Cloneable, Iterable<E>, Collection<E>, NavigableSet<E>, Set<E>, SortedSet<E> interfaces.
 * b. It does not allow null elements, because null arguments and return values cannot be reliably distinguished from the
 * absence of elements.
 * c. Its implementation provides average log(n) time cost for contains, add, and remove operations and their variants.
 * d. It is thread-safe.
 * e. It should be preferred over implementing Set interface when concurrent modification of set is required.
 */
public class ConcurrentSkipListSetDemo {

    public static void main(String[] args) {

        // Initializing the set using ConcurrentSkipListSet()
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        set.add(78);
        set.add(64);
        set.add(12);
        set.add(45);
        set.add(8);

        System.out.println("ConcurrentSkipListSet: " + set);

        System.out.println("The highest element of the set: " + set.last());

        // Retrieving and removing first element of the set
        System.out.println("The first element of the set: " + set.pollFirst());

        if (set.contains(9))
            System.out.println("9 is present in the set.");
        else
            System.out.println("9 is not present in the set.");

        System.out.println("Number of elements in the set = "
                + set.size());
    }

}
