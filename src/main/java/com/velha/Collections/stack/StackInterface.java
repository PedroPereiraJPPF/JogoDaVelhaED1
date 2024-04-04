package com.velha.collections.stack;

public interface StackInterface <T>
{
    int size();
    boolean isEmpty();
    boolean isFull();

    T peek() throws Exception;
    T pop() throws Exception;
    void push(T element ) throws Exception;
}
