package com.velha.collections.queue;

import java.io.Serializable;

import com.velha.collections.linkedList.LinkedListInterface;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;

public class LinkedQueue<T> implements QueueInterface<T>, Serializable {
    private LinkedListInterface<T> list;

    public LinkedQueue() {
        this.list = new SinglyLinkedList<>();
    }

    @Override
    public void add(T element) throws Exception {
        this.list.addLast(element);
    }

    @Override
    public T remove() throws Exception {
        return this.list.removeFirst();
    }

    @Override
    public T peek() throws Exception {
        return this.list.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean isFull() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFull'");
    }

    @Override
    public int size() {
        return list.size();
    }    
}
