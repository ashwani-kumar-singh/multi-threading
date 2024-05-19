package collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The PriorityQueue is based on the priority heap. The elements of the priority queue are ordered
 * according to the natural ordering, or by a Comparator provided at queue construction time,
 * depending on which constructor is used.
 * <p>
 * A few important points on Priority Queue are as follows:
 * <p>
 * PriorityQueue doesn’t permit null.
 * We can’t create a PriorityQueue of Objects that are non-comparable
 * PriorityQueue are unbound queues.
 * <p>
 * Task Scheduling, Hospital Emergency Room, Network Routing, Traffic Management Systems,
 * Job Scheduling Systems, Shipping  Management
 *
 * @link: <a href="https://www.geeksforgeeks.org/priority-queue-class-in-java/">...</a>
 */

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Student> pq = new PriorityQueue<>(5, Comparator.comparing(Student::getCgpa).reversed());
        pq.add(new Student("Nandini", 3.2));
        pq.add(new Student("Anmol", 3.6));
        pq.add(new Student("Palak", 4.0));

        // Printing names of students in priority order,poll() method is used to access the head element of queue
        System.out.println("Students served in their priority order");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll().getName());
        }
    }
}

class StudentComparator implements Comparator<Student> {

    // Overriding compare()method of Comparator for descending order of cgpa
    public int compare(Student s1, Student s2) {
        if (s1.cgpa < s2.cgpa)
            return 1;
        else if (s1.cgpa > s2.cgpa)
            return -1;
        return 0;
    }
}

class Student {
    public String name;
    public double cgpa;

    public Student(String name, double cgpa) {

        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }
}
