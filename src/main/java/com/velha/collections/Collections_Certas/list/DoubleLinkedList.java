package com.velha.collections.Collections_Certas.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoubleLinkedList<T> implements InterfaceList<T>{

	public class Node {
		T data;
		Node next = null;
		Node prev = null;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (head == null && tail == null) {
			return true;
		}
		return false;
	}

	//TODO implementar algoritmo de busca melhor que o linear
	@Override
	public boolean contains(Object o) {
		if (isEmpty()) {
			return false;
		}
		Node iterator = head; 
		
		while (iterator != null) {
			if (iterator.data.equals(o)) {
				return true;
			}
			iterator = iterator.next;
		}
		
		return false;
	}

	//pulando iterator
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		if (isEmpty()) {
			//TODO Exception?
			return new Object[0];
		}
		
		Object[] array = new Object[size];
		Node iterator = head;
		
		for(int i = 0; i < size; i++) {
			array[i] = iterator.data;
			iterator = iterator.next;
		}
		
		return array;
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		if (isEmpty()) {
			//TODO Exception?
			return (T[]) new Object[0];
		}
		if (a.length < size) {
			a = (T[]) new Object[size];
		}

		Node iterator = head;
		
		for(int i = 0; i < size; i++) {
			a[i] = (T) iterator.data;
			iterator = iterator.next;
		}
		
		return a;
	}

	@Override
	public boolean add(T e) {
		Node element = new Node(e);
		
		if (isEmpty()) {
			head = element;
			tail = element;
		} else {
			tail.next = element;
			element.prev = tail;
			tail = element;
		}
		size++;
		
		return true;
	}

	@Override
	public boolean remove(Object o) {
		
		if (isEmpty()) {
			return false;
		} else if (head == tail) {
			head = null;
			tail = null;
			
			size--;
			return true;			
		} else if (head.data.equals(o)) {
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			
			size--;
			return true;
		} else if (tail.data.equals(o)){
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			
			size--;
			return true;
		}
		
		Node iterator = head;
		
		while (iterator != null) {
			if (iterator.data.equals(o)) {
				iterator.prev.next = iterator.next;
				iterator.next.prev = iterator.prev;
				
				iterator.next = null;
				iterator.prev = null;
				
				size--;
				return true;
			}
			iterator = iterator.next;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		for(Object e : c) {
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		
		for(T e : c) {
			if (!add(e)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
				
		for(T e : c) {
			if (!addAfter(index, e)) {
				return false;
			}
			index++;
		}
		
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		for(Object e: c) {
			if (!remove(e)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (isEmpty()) {
			return false;
		} else {			
			Node iterator = head;
			
			while(iterator != null) {
				if (!c.contains(iterator.data)) {
					remove(iterator.data);
				}
				iterator = iterator.next;
			}
			
			return true;
		}
	}

	@Override
	public void clear() {
		Node iterator = head;
		
		while(iterator != null) {
			remove(iterator.data);
			iterator = iterator.next;
		}
	}

	@Override
	public T get(int index) {
		if (isEmpty()) {
			return null;
		}	
		Node iterator = head;
		
		for (int i = 0; i < index; i++) {
			iterator = iterator.next;
		}
		
		return iterator.data;
	}

	@Override
	public T set(int index, T element) {
		if (isEmpty()) {
			return null;
		}	
		Node iterator = head;
		
		for (int i = 0; i < index; i++) {
			iterator = iterator.next;
		}
		T r = iterator.data;
		iterator.data = element;
		
		return r;
	}

	@Override
	public void add(int index, T e) {
		if (index > size) {
			//TODO exception?
		} else if (index == 0){
			Node element = new Node(e);
			
			head.prev = element;
			element.next = head;
			head = element;
			
			size++;
		} else if (index == size - 1) {
			Node element = new Node(e);
			
			tail.next = element;
			element.prev = tail;
			tail = element;
			
			size++;
		} else {
			Node iterator = head;
			Node element = new Node(e);
			
			for(int i = 0; i < index; i++) {
				iterator = iterator.next;
			}
			
			iterator.next.prev = element;
			element.next = iterator.next;
			iterator.next = element;
			element.prev = iterator;
			
			size++;
		}
	}

	@Override
	public T remove(int index) {
		if (isEmpty() || index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			Node r = head;
			
			head = head.next;
			head.prev = null;
			r.next = null;
			
			return r.data;
		} else if (index == size - 1) {
			Node r = tail;
			
			tail = tail.prev;
			tail.next = null;
			r.prev = null;
			
			return r.data;
		} else {
			Node iterator = head;
			
			for(int i = 0; i < index; i++) {
				iterator = iterator.next;
			}
			
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			iterator.next = null;
			iterator.prev = null;
			
			return iterator.data;
		}
	}

	@Override
	public int indexOf(Object o) {
		if (isEmpty()) {
			return -1;
		} 
		if (!contains(o)) {
			return -1;
		}
		if (head.data.equals(o)) {
			return 0;
		} else if (tail.data.equals(o)) {
			return size - 1;
		} else {
			Node iterator = head;
			
			for (int i = 0; iterator != null; i++) {
				if (iterator.data.equals(o)) {
					return i;
				}
				iterator = iterator.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (isEmpty()) {
			return -1;
		} 
		if (contains(o)) {
			Node iterator = head;
			int position = -1;
			
			for (int i = 0; iterator != null; i++) {
				if (iterator.data.equals(o)) {
					position = i;
				}
				iterator = iterator.next;
			}
			
			return position;
		}
		
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(T element) {
		Node e = new Node(element);
		
		if (isEmpty()) {			
			head = e;
			tail = e;
		} else {
			head.prev = e;
			e.next = head;
			head = e;
		}
	}

	@Override
	public void addLast(T element) {
		add(element);
	}
	
	public boolean addAfter(int index, T e) {
		if (index > size) {
			return false;
		} else if (index == 0){
			Node element = new Node(e);
			
			head.prev = element;
			element.next = head;
			head = element;
			
			size++;
			return true;
		} else if (index == size - 1) {
			Node element = new Node(e);
			
			tail.next = element;
			element.prev = tail;
			tail = element;
			
			size++;
			return true;
		} else {
			Node iterator = head;
			Node element = new Node(e);
			
			for(int i = 0; i < index; i++) {
				iterator = iterator.next;
			}
			
			iterator.next.prev = element;
			element.next = iterator.next;
			iterator.next = element;
			element.prev = iterator;
			
			size++;
			return true;
		}
	}

	@Override
	public T peekFirst() {
		return head.data;
	}

	@Override
	public T peekLast() {
		return tail.data;
	}

	@Override
	public T search(T crit) {
		// Similar ao Get ou IndexOf
		return null;
	}

	@Override
	public T removeFirst() {
		// TODO remove faz isso
		return null;
	}

	@Override
	public T removeLast() {
		// TODO remove faz isso
		return null;
	}

	@Override
	public void show() {
		if (isEmpty()) {
			System.out.println("A lista está vazia");
		} else {
			Node iterator = head;
			
			System.out.print("[ ");
			while(iterator != null) {
				System.out.print(iterator.data);
				if (iterator.next != null) {
					System.out.print(", ");
				}
				iterator = iterator.next;
			}
			System.out.println(" ]");
		}
	}

	@Override
	public void showReverse() {
		if (isEmpty()) {
			System.out.println("A lista está vazia");
		} else {
			Node iterator = tail;
			
			System.out.print("[ ");
			while(iterator != null) {
				System.out.print(iterator.data);
				if (iterator.prev != null) {
					System.out.print(", ");
				}
				iterator = iterator.prev;
			}
			System.out.println(" ]");
		}
	}

}
