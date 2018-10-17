package adt.VariationsStackandQueue;

import adt.queue.Queue;
import adt.queue.QueueDoubleLinkedListImpl;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.stack.Stack;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class StackWithTwoQueue<T> implements Stack<T> {

	QueueDoubleLinkedListImpl<T> queue;
	QueueDoubleLinkedListImpl<T> aux;
	
	public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
		StackWithTwoQueue<Integer> stack = new StackWithTwoQueue<Integer>(5);

		System.out.println(stack.top());

	}

	public StackWithTwoQueue(int size) {

		this.queue = new QueueDoubleLinkedListImpl<T>(size);
		this.aux = new QueueDoubleLinkedListImpl<T>(size);
		

	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			try {
				while (!queue.isEmpty()) {
					aux.enqueue(queue.dequeue());
				}
				queue.enqueue(element);
				while (!aux.isEmpty()) {
					queue.enqueue(aux.dequeue());
				}

			} catch (QueueOverflowException | QueueUnderflowException e) {
				e.printStackTrace();
			}

		} else {
			throw new StackOverflowException();
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T element = queue.head();
			try {
				queue.dequeue();
			} catch (QueueUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return element;
		} else {
			throw new StackUnderflowException();
		}

	}

	@Override
	public T top() {
		T top = null;
		if (!isEmpty()) {
			top = queue.head();
		}
		return top;
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.queue.isFull();
	}

}
