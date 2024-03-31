package com.velha.Collections.linkedList;

public interface LinkedListInterface<T> {
        void addFirst(T element);
        void addLast(T element);
        void addAfter(T element, T crit) throws Exception;

        T peekFirst() throws Exception;
        T peekLast() throws Exception;

        T search(T crit) throws Exception;

        T removeFirst();
        T removeLast();
        T remove(T element);
        boolean isEmpty();
}
