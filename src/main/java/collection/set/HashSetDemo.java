package collection.set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashSetDemo<E> {

    private static final Object PRESENT = new Object();
    private final transient Map<E, Object> map;

    public HashSetDemo() {
        map = new HashMap<>();
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(E e) {
        return map.remove(e) == PRESENT;
    }

    public boolean contains(E e) {
        return map.containsKey(e);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
}
