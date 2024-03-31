package com.velha.Collections.linkedList.doubleLinkedList;

import com.velha.Collections.linkedList.LinkedListInterface;

public class DoubleLinkedList<T> implements LinkedListInterface<T> {
    DoubleLinkedNode<T> head, tail;
    int size;

    public DoubleLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(T element) {
        DoubleLinkedNode<T> newElement = new DoubleLinkedNode<T>(element, null, null);

        if (size == 0) {
            this.head = this.tail = newElement;
        } else {
            newElement.after = this.head;
            this.head.before = newElement;
            this.head = newElement;
        }

        size++;
    }

    @Override
    public void addLast(T element) {
        if (size == 0) {
            this.head = this.tail = new DoubleLinkedNode<T>(element, null, null);
        } else {
            this.tail.after = new DoubleLinkedNode<T>(element, this.tail, null);
            this.tail = this.tail.after;
        }

        size++;
    }

    @Override
    public void addAfter(T element, T crit) throws Exception {
        DoubleLinkedNode<T> iterator = this.head;

        while(!iterator.element.equals(crit)) {
            if (iterator.after == null) {
                throw new Exception("Elemento não encontrado");
            }

            iterator = iterator.after;
        }

        DoubleLinkedNode<T> newElement = new DoubleLinkedNode<T>(element, iterator, iterator.after);
        iterator.after.before = newElement;
        iterator.after = newElement;
    }

    @Override
    public T peekFirst() throws Exception {
        return this.head.element;
    }

    @Override
    public T peekLast() throws Exception {
        return this.tail.element;
    }

    @Override
    public T search(T crit) throws Exception {
        DoubleLinkedNode<T> iterator = this.head;

        while(!iterator.element.equals(crit)) {
            if (iterator.after == null) {
                throw new Exception("Elemento não encontrado");
            }

            iterator = iterator.after;
        }

        return iterator.element;
    }

    @Override
    public T removeFirst() {
        T removedElement = this.head.element;

        size--;

        if (this.head == this.tail) {
            this.head = this.tail = null;

            return removedElement;
        }

        this.head = this.head.after;
        this.head.before = null;

        return removedElement;
    }

    @Override
    public T removeLast() {
        T removedElement = this.tail.element;

        size--;

        if (this.head == this.tail) {
            this.head = this.tail = null;

            return removedElement;
        }

        this.tail = this.tail.before;
        this.tail.after = null;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        DoubleLinkedNode<T> iterator = this.head;
        DoubleLinkedNode<T> elementBefore = null;

        if (element.equals(head.element)) {
            return removeFirst();
        }

        if (element.equals(tail.element)) {
            return removeLast();
        }

        while(!iterator.element.equals(element)) {
            if (iterator.after == null) {
                return null;
            }
            elementBefore = iterator;
            iterator = iterator.after;
        }

        elementBefore.after = iterator.after;
        elementBefore.after.before = elementBefore;

        size--;

        return iterator.element;
    }
    
    public void iterate() {
        DoubleLinkedNode<T> iterator = this.head;
        while(iterator != null) {
            System.out.println(iterator.element);
            iterator = iterator.after;
        }
    }

    public void iterateReverse() {
        DoubleLinkedNode<T> iterator = this.tail;
        while(iterator != null) {
            System.out.println(iterator.element);
            iterator = iterator.before;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}