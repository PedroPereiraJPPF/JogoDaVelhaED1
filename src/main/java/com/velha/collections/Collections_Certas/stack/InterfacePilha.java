package com.velha.collections.Collections_Certas.stack;

public interface InterfacePilha <T>{

	void push(T element) throws Exception;
	T pop() throws Exception;
	
	T peek() throws Exception;
	
	boolean isEmpty();
	boolean isFull();
	
	void show();
	int size();
}
