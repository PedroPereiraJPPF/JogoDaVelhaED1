package com.velha.Collections.stack;

public interface StackInterface <T>
{
    boolean isEmpty();
    boolean isFull();

    T peek() throws Exception;
    T pop() throws Exception;
    void push(T element ) throws Exception;
}
