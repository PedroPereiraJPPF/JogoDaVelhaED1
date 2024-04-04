package com.velha.collections.collections_Certas.stack;

public class PilhaEncadeada<T> implements InterfacePilha<T> {
	
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
	
	public PilhaEncadeada() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void push(T element) throws Exception {
		Node e = new Node(element);
		
		if(isEmpty()) {
			head = e;
			tail = e;
			
			size++;
		} else {
			e.next = head;
			head.prev = e;
			head = e;
			
			size++;
		}
	}

	@Override
	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("A pilha está Vazia!");
		} else {
			Node removedElement = head;
			
			head = head.next;
			removedElement.next = null;
			removedElement.prev = null;
			
			size--;
			return removedElement.data;
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
		// TODO Não existe
		return false;
	}

	@Override
	public void show() {
		Node iterator = head;
		StringBuilder bld = new StringBuilder();
		
		while (iterator != null) {
			bld.append(iterator.data + " ");
			iterator = iterator.next;
		}
		
		System.out.println("[" + bld + "]");
	}

	public int size(){
		return size;
	}
}
