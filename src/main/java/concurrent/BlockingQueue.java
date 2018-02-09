package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
    private final T[] items;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private int count;
    private int takeIndex;
    private int putIndex;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("invalid capacity "+ capacity);
        items = (T[]) new Object[capacity];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void offer(T item) {
        if (item == null) throw new NullPointerException();
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putIndex] = item;
            count++;
            if (++putIndex == items.length) putIndex = 0;
            notEmpty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T poll() {
        lock.lock();
        T item = null;
        try {
            while (count == 0) {
                notEmpty.await();
            }
            item = items[takeIndex];
            items[takeIndex] = null;
            count--;
            if (++takeIndex == items.length) takeIndex = 0;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return item;
    }

}
