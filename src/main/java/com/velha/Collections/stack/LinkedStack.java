package com.velha.collections.stack;

import com.velha.collections.linkedList.LinkedListInterface;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;

public class LinkedStack<T> implements StackInterface<T> {
    LinkedListInterface<T> list;

    public LinkedStack() {
        this.list = new SinglyLinkedList<>();
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
    public T peek() throws Exception {
        return this.list.peekLast();
    }

    @Override
    public T pop() throws Exception {
        return this.list.removeLast();
    }

    @Override
    public void push(T element) throws Exception {
        this.list.addLast(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }
    
}
