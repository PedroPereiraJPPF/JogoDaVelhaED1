package com.velha.collections.Collections_Certas.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SingleLinkedList<T> implements InterfaceList<T>{

	class Node {
		T data;
		Node next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public SingleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if (this.head == null && this.tail == null) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if(!isEmpty()) {
			Node iterator = head;
			
			while (!iterator.data.equals(o)) 
			{
				iterator = iterator.next;
				
				if (iterator == null) 
				{
					return false;
				}
			}
			
			return true;
			
		} else {
			return false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		
		return null;
	}

	@Override
	public Object[] toArray() {		
		if (!isEmpty()) {
			Node iterator = head;
			Object[] array = new Object[size];
			
			for(int j = 0; iterator != null; j++) {
				array[j] = iterator.data;
				
				iterator = iterator.next;
			}
			
			return array;
			
		} else {
			return new Object[size];
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		if (!isEmpty()) {
			Node iterator = head;
			if (a.length < size) {
				a = (T[]) new Object[size];
			}
			
			for(int j = 0; iterator != null; j++) {
				a[j] = (T) iterator.data;
				
				iterator = iterator.next;
			}
			
			return a;
			
		} else {
			return a;
		}
	}

	@Override
	public boolean add(T e) {
		Node newElement = new Node(e);
	
		try {
			if (head == null && tail == null) {
				head = newElement;
				tail = newElement;
			}
			tail.next = newElement;
			this.tail = newElement;
			
			size++;
				
			return true;
		} catch (Exception ex){
			return false;
		}
	}

	@Override
	public boolean remove(Object o) {
		if (o.equals(head.data)) {
			removeFirst();
			return true;
		} else if (o.equals(tail.data)) {
			removeLast();
			return true;
		}
		if (!isEmpty()) {
			Node iterator = head;
			Node control = null;
				
			while(!iterator.data.equals(o)) {
				control = iterator;
				iterator = iterator.next;
				
				if (iterator == null) {
					return false;
				}
			}
			
			control.next = iterator.next;
			iterator.next = null;
			
			size--;
			return true;
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
		
		if (index > this.size) {
			//TODO exception
			return false;
		}
		
		Node iterator = this.head;
		
		for(T e : c) {
			Node element = new Node(e);
			
			for (int i = 0; i < index - 1; i++) {
				iterator = iterator.next;
			}
			
			element.next = iterator.next;
			iterator.next = element;
			
			index++;
			this.size++;
		}
		
		while(this.tail.next != null) {
			this.tail = this.tail.next;
		}
		
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (isEmpty()) {
			return false;
		}
		
		Node control = null;
		Node iterator = head;
		
		for (Object e : c) {
			
			while(iterator != null) {				
				if (iterator.data.equals(e)) {
					if (iterator.equals(head)) {
						head = head.next;
						iterator.next = null;
						iterator = head;
					} else if(iterator.equals(tail)) {
						tail = control;
						control.next = null;
					} else {
						control.next = iterator.next;
						iterator.next = null;
					}
					
					size--;
				}
				control = iterator;
				iterator = iterator.next;
			}
		}
		
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(isEmpty()) {
			return false;
		}
		
		Node control = null;
		Node iterator = head;
		
		while(iterator != null) {			
			if (!c.contains(iterator.data)) {
				if (iterator.equals(head)) {
					head = head.next;
					iterator.next = null;
					iterator = head;
				} else if (iterator.equals(tail)){
					tail = control;
					control.next = null;
				} else {
					control.next = iterator.next;
					iterator.next = null;
				}
				
				size--;
			}
			
			control = iterator;
			iterator = iterator.next;
		}
		
		return true;
	}

	@Override
	public void clear() {
		Node iterator = head;
		Node control = null;
		
		while (iterator != null) {
			control = iterator.next;
			iterator.next = null;
			iterator = control;
		}
		
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public T get(int index) {
		Node iterator = head;
		
		for (int i = 0; i < index - 1; i++) {
			iterator = iterator.next;
		}
		
		return iterator.data;
	}

	@Override
	public T set(int index, T element){
		Node iterator = head;
		
		if (isEmpty() || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i < index - 1; i++) {
			iterator = iterator.next;
		}
		
		T removedElement = iterator.data;
		iterator.data = element;
		
		return removedElement;
	}

	@Override
	public void add(int index, T element) {		
		if (isEmpty() || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node iterator = head;
		Node e = new Node(element);
		
		for (int i = 0; i < index - 1; i++) {
			iterator = iterator.next;
		}
		
		e.next = iterator.next;
		iterator.next = e;
		size++;
	}

	@Override
	public T remove(int index) {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node iterator = head;
		Node control = null;
		
		for (int i = 0; i < index - 1; i++) {
			control = iterator;
			iterator = iterator.next;
		}
		
		control.next = iterator.next;
		iterator.next = null;
		
		return iterator.data;
	}

	@Override
	public int indexOf(Object o) {

		if (isEmpty()) {
			return -1;
		}
		
		Node iterator = head;
		
		for (int i = 0; iterator != null; i++) {
			if (iterator.data.equals(o)) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {

		if (isEmpty()) {
			return -1;
		}
		
		Node iterator = head;
		int position = -1;
		
		for (int i = 0; iterator != null; i++) {
			if (iterator.data.equals(o)) {
				position = i;
			}
		}
		
		return position;
	}

	@Override
	public ListIterator<T> listIterator() {
		
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		
		return null;
	}

	//TODO 1 -> 5 ou 0 -> 4
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		
		if (toIndex > size || isEmpty() || (toIndex - fromIndex) < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		List<T> lista = new SingleLinkedList<>();
		Node iterator = head;
		
		for (int i = 0; i < fromIndex; i++) {
			iterator = iterator.next;
		}
		for (int i = fromIndex; i < toIndex; i++) {
			lista.add(iterator.data);
			iterator = iterator.next;
		}
		
		return lista;
	}

	@Override
	public void addFirst(T element) {
		Node newElement = new Node(element);
		
		try {
			if (head == null && tail == null) {
				head = newElement;
				tail = newElement;
			} else {
				newElement.next = head;
				this.head = newElement;
			}
			
			size++;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLast(T element) {
		Node newElement = new Node(element);
		
		try {
			if (head == null && tail == null) {
				head = newElement;
				tail = newElement;
			}
			tail.next = newElement;
			this.tail = newElement;
			
			
			size++;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T peekFirst() throws NullPointerException{
		if (!isEmpty()){
			return head.data;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public T peekLast() throws NullPointerException{
		if (!isEmpty()){
			return tail.data;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public T search(T crit) throws NullPointerException{
		if (!isEmpty()) {
			Node iterator = head;
			
			while(!iterator.data.equals(crit)) {
				iterator = iterator.next;
			}
			
			return iterator.data;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public T removeFirst() throws NullPointerException{		
		if (!isEmpty()) {
			T RemovedElement;
			RemovedElement = head.data;
			head = head.next;
			
			size--;
			return RemovedElement;
		} else {
			throw new NullPointerException();
		}
	}

	@Override	
	public T removeLast() {
		if (!isEmpty()) {
			T removedElement;
			Node iterator = this.head;
			
			removedElement = tail.data;
			
			while (iterator.next != tail) {
				iterator = iterator.next;
			}
			
			tail = iterator;
			tail.next = null;
			
			size--;
			return removedElement;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void show() throws NullPointerException{
		
		
		if (!isEmpty()) {
			Node iterator = head;
			
			while(iterator != null) {
				System.out.print(iterator.data + " ");
				
				iterator = iterator.next;
			}
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void showReverse() throws NullPointerException{
		if (isEmpty()) {
			Node iterator = head;
			@SuppressWarnings("unchecked")
			T[] dados = (T[]) new Object[size];
			
			for(int j = 0; iterator != null; j++) {
				dados[j] = iterator.data;
				
				iterator = iterator.next;
			}
			
			for (int j = dados.length - 1; j >= 0; j--) {
				System.out.print(dados[j] + " ");
			}
		} else {
			throw new NullPointerException();
		}
	}

}
