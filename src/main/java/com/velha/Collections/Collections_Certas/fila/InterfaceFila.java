package com.velha.Collections.Collections_Certas.fila;

public interface InterfaceFila <T>{
	void add(T element) throws Exception;
	T remove() throws Exception;
	
	T peek() throws Exception;
	
	boolean isEmpty();
	boolean isFull();
	
	void show(); //opcional e auxiliar
}
