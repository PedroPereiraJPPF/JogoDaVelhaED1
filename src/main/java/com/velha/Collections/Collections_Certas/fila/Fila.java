package com.velha.Collections.Collections_Certas.fila;

public class Fila<T> implements InterfaceFila<T>{

	int inicio;
	int fim;
	int size;
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public Fila(int size) {
		array = (T[]) new Object[size];
		
		this.size = size;
		this.inicio = -1;
		this.fim = -1;
	}
	
	@Override
	public void add(T element) throws Exception {
		int fc = (this.fim + 1) % this.size;
		
		if (fc == this.inicio) {
			throw new Exception("A fila está cheia!");
		}
		
		this.fim = fc;
		array[fim] = element;
		
		if (this.inicio == -1) {
			this.inicio = 0;
		}
	}

	@Override
	public T remove() throws Exception {		
		if (this.inicio == -1) {
			throw new Exception("A fila está vazia!");
		}
		
		T oldinicio = array[this.inicio];
		
		if (this.inicio == this.fim) {
			this.fim = -1;
			this.inicio = -1;
		} else {
			this.inicio = (this.inicio + 1) % size;
		}
	
		return oldinicio;
	}

	@Override
	public T peek() throws Exception {
		if (this.inicio == this.fim) {
			throw new Exception("A fila está vazia!");
		} else {
			return array[this.inicio];
		}
	}

	@Override
	public boolean isEmpty() {
		if (this.inicio == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		int fc = (this.fim + 1) % this.size;
		
		if (fc == this.inicio) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public int getInicio() {
		return inicio;
	}

	public int getFim() {
		return fim;
	}
	
}
