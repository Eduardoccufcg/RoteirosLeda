package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;


public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.top = new DoubleLinkedListImpl<T>();
		this.size = size;
		
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (!isFull()) {
				top.insertFirst(element);
			} else {
				throw new StackOverflowException();
			}

		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T retorno = (T) ((DoubleLinkedListImpl<T>) top).getHead().getData();
			top.removeFirst();
			return retorno;

		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		T topo = null;
		if (!isEmpty()) {
			topo = (T) ((DoubleLinkedListImpl<T>) top).getHead().getData();
		}
		return topo;

	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (size == top.size());
	}

}
