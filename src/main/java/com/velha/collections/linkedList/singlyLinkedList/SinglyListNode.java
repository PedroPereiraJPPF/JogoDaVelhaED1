package com.velha.collections.linkedList.singlyLinkedList;

import java.io.Serializable;

public class SinglyListNode<T> implements Serializable {
    public T element;
    public SinglyListNode<T> next;

    SinglyListNode () {

    }

    SinglyListNode(T element, SinglyListNode<T> next) {
        this.element = element;
        this.next = next;
    }
}
