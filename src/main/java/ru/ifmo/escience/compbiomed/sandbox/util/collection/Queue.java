package ru.ifmo.escience.compbiomed.sandbox.util.collection;

import ru.ifmo.escience.compbiomed.sandbox.simulation.Event;

import java.util.LinkedList;

public class Queue<T extends Event> {

    private LinkedList<T> queue = new LinkedList<>();

    public void offer(T item) {
        if (queue.isEmpty()) {
            queue.offer(item);
        } else {
            final T last = queue.peekLast();
            if (item.eTime() >= last.eTime()) {
                queue.offer(item);
            } else {
                for (int i = 0; i < queue.size(); ++i) {
                    final T current = queue.get(i);
                    if (item.eTime() < current.eTime()) {
                        queue.add(i, item);
                        break;
                    }
                }
            }
        }
    }

    public T poll() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
