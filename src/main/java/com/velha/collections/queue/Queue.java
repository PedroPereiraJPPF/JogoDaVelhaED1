package com.velha.collections.queue;

public class Queue<T> implements QueueInterface<T> {
    private T[] array;
    private int size, first, last;

    @SuppressWarnings("unchecked")
    public Queue(int size) {
        this.array = (T[]) new Object[size];
        this.size = size;
        this.first = this.last = -1;
    }

    @Override
    public void add(T element) throws Exception {
        if (isFull())
            throw new Exception();

        if (first == -1) 
            first = 0;

        last = (last + 1) % size;
        array[last] = element;
    }

    @Override
    public T remove() throws Exception {
        if (isEmpty())
            throw new Exception();

        int firstBefore = first;

        if (last == -1)
            last = 0; 
        
        if (first == last) {
            first = last = -1;
        } else {
            first = (first + 1) % size;
        }
            
        return array[firstBefore];
    }

    @Override
    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Não pode pegar o topo de uma pilha vazia");
        }

        return array[first];
    }

    @Override
    public boolean isEmpty() {
        return first == -1;
    }

    @Override
    public boolean isFull() {
        return ((last + 1) % size == first);
    }

    @Override
    public int size() {
       return this.size;
    }
    
}
