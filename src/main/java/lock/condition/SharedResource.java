package lock.condition;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    Stack<Integer> stack = new Stack<>();
    int CAPACITY = 5;
    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(Integer item) {
        try {
            lock.lock();
            while (stack.size() == CAPACITY) {
                System.out.println("Stack is Full !!");
                stackFullCondition.await();
            }
            stack.push(item);
            System.out.println("element: " + item + " added by thread: " + Thread.currentThread().getName());
            stackEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public Integer popFromStack() {
        try {
            lock.lock();
            while (stack.size() == 0) {
                System.out.println("Stack is Empty !!");
                stackEmptyCondition.await();
            }
            return stack.pop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}
