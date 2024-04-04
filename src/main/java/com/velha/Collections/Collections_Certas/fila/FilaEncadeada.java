package com.velha.Collections.Collections_Certas.fila;

public class FilaEncadeada <T> implements InterfaceFila<T>{
	
	class Node {
		T data;
		Node next;
		Node prev;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	Node head;
	Node tail;
	int size;
	
	public FilaEncadeada() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void add(T element) throws Exception {
		Node e = new Node(element);
		
		if (isEmpty()) {
			head = e;
			tail = e;
			
			size++;
		} else {
			tail.next = e;
			e.prev = tail;
			tail = e;
			
			size++;
		}
	}

	@Override
	public T remove() throws Exception {
		if (!isEmpty()) {
			Node removedElement = head;

			head = head.next;
			removedElement.next = null;
			head.prev = null;
			
			return removedElement.data;
		} else {
			throw new Exception("A pilha está vazia!");
		}
	}

	@Override
	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("Não há elemento para retornar");
		} else {
			return head.data;
		}
	}

	@Override
	public boolean isEmpty() {
		if (head == null && tail == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void show() {
		Node iterator = tail;
		StringBuilder bld = new StringBuilder();
		
		while (iterator != null) {
			bld.append(iterator.data + " ");
			iterator = iterator.prev;
		}
		
		System.out.println("[" + bld + "]");
	}
}
