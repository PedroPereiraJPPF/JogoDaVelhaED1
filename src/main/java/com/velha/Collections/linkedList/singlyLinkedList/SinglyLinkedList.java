package com.velha.Collections.linkedList.singlyLinkedList;

import com.velha.Collections.linkedList.LinkedListInterface;

public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    private SinglyListNode<T> head, tail;
    private int size;

    public SinglyLinkedList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void addFirst(T element) {
        this.head = new SinglyListNode<T>(element, this.head);

        if (size == 0) {
            this.tail = this.head;
        }

        size++;
    }

    @Override
    public void addLast(T element) {
        SinglyListNode<T> newElement = new SinglyListNode<T>(element, null);

        if (size == 0) {
            this.head = this.tail = newElement;
        } else {
            tail.next = new SinglyListNode<T>(element, null);
            this.tail = tail.next;
        }
        
        size++;
    }

    @Override
    public void addAfter(T element, T crit) throws Exception {
        if (isEmpty()) {
            throw new Exception("Não existe elementos nessa lista");
        }

        SinglyListNode<T> iterator = this.head;

        while (!iterator.element.equals(crit)) {
            iterator = iterator.next;
            if (iterator == null) {
                throw new Exception("O elemento não existe na lista");
            }
        }

        iterator.next = new SinglyListNode<T>(element, iterator.next);
    }

    @Override
    public T peekFirst() throws Exception {
        if (isEmpty()) {
            throw new Exception("Lista vazia");
        }

        return this.head.element;
    }

    @Override
    public T peekLast() throws Exception {
        if (isEmpty()) {
            throw new Exception("Lista vazia");
        }

        return this.tail.element;
    }

    @Override
    public T search(T crit) throws Exception {
        if (isEmpty()) {
            throw new Exception("Lista vazia");
        }

        SinglyListNode<T> iterator = this.head;

        while(!iterator.element.equals(crit)) {
            iterator = iterator.next;
        }

        return iterator.element;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T firstBefore = this.head.element;

        this.head = this.head.next;

        return firstBefore;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = this.tail.element;
        SinglyListNode<T> beforeLast = null;
        SinglyListNode<T> iterator = this.head;

        while (iterator != this.tail) {
            beforeLast = iterator;
            iterator = iterator.next;
        }

        beforeLast.next = null;
        this.tail = beforeLast;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            return null;
        }

        if (element.equals(this.head.element)) {
            return removeFirst();
        }

        SinglyListNode<T> beforeLast = null;
        SinglyListNode<T> iterator = this.head;

        while (!iterator.element.equals(element)) {
            beforeLast = iterator;

            if (iterator.next == null) {
                return null;
            }

            iterator = iterator.next;
        }

        beforeLast.next = iterator.next;

        return iterator.element;
    }

    public boolean isEmpty() {
        return size == 0;
    } 

    public void iterate() {
        SinglyListNode<T> iterator = this.head;
        while(iterator != null) {
            System.out.println(iterator.element);
            iterator = iterator.next;
        }
    }
    
}
