package com.velha.collections.stack;

public class Stack<T> implements StackInterface<T> {
    private T[] array;
    private int size;
    private int position;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.array = (T[]) new Object[size];
        this.size = size;
        this.position = -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return position == -1;
    }

    @Override
    public boolean isFull() {
        return position == size -1;
    }

    @Override
    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }

        return this.array[position];
    }

    @Override
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Não pode remover elementos de uma pilha vazia");
        }

        T removedElement =  this.array[position];

        this.position--;

        return removedElement;
    }

    @Override
    public void push(T element) throws Exception {
        if (isFull()) {
            throw new Exception("Não pode adicioanr em uma pilha cheia");
        }

        this.position++;

        this.array[position] = element;
    }
}
