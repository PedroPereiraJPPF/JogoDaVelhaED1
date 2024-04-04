package com.velha.collections.queue;

public interface QueueInterface<T> {
    void add(T element) throws Exception;
    T remove() throws Exception;
    
    T peek() throws Exception;
    
    boolean isEmpty();
    boolean isFull();
}
