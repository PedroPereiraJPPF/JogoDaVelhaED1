package com.velha.collections.collections_Certas.list;

import java.util.List;

public interface InterfaceList <T> extends List<T>{
	void addFirst(T element);
	void addLast(T element);
	
	T peekFirst();
	T peekLast();
	
	T search(T crit);
	
	T removeFirst();
	T removeLast();
	
	void show();
	void showReverse();
}
