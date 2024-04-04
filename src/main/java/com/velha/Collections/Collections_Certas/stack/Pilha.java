package com.velha.Collections.Collections_Certas.stack;

public class Pilha<T> implements InterfacePilha<T>{

	private T[] array;
	private int top;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Pilha(int size) {
		this.array = (T[]) new Object[size];
		
		this.size = size;
		this.top = -1;
	}
	
	/**
	 * Pushes an element into the top of the stack.
	 * <p>
	 * 
	 * @param element   A element to be inserted in the stack
	 **/
	public void push(T element) throws Exception {
		if (isFull()) {
			throw new Exception("A pilha está cheia");
		} else {
			top++;
			
			array[this.top] = element;
		}
	}

	@Override
	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("A pilha está vazia");
		} else {
			T element = array[this.top];
			
			top--;
			
			return element;
		}
	}

	@Override
	public T peek() throws Exception {
		
		if (isEmpty()) {
			throw new Exception("A pilha está vazia");
		} else {
			return array[this.top];
		}
	}

	@Override
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.top == (this.size - 1)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void show() {
		if (isEmpty()) {
			System.out.println("A pilha está vazia");
		} else {
			System.out.print("[ ");
			for(int i = 0; i <= top; i++) {
				System.out.print(this.array[i] + " ");
			}
			
			System.out.println("]");
		}
		
	}

	@Override
	public int size(){
		return size;
	}
}
