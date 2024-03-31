package com.velha.Collections.linkedList.singlyLinkedList;

public class SinglyListNode<T> {
    public T element;
    public SinglyListNode<T> next;

    SinglyListNode () {

    }

    SinglyListNode(T element, SinglyListNode<T> next) {
        this.element = element;
        this.next = next;
    }
}
