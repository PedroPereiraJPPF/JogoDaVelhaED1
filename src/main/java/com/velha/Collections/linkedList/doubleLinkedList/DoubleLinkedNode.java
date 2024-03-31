package com.velha.Collections.linkedList.doubleLinkedList;

public class DoubleLinkedNode<T> {
    public T element;
    public DoubleLinkedNode<T> before, after;

    public DoubleLinkedNode() {
        element = null;
        before = after = null;
    }

    public DoubleLinkedNode(T element, DoubleLinkedNode<T> before, DoubleLinkedNode<T> after) {
        this.element = element;
        this.before = before;
        this.after = after;
    }
}
