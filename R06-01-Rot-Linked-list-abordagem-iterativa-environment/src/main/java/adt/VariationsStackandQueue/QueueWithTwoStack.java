package adt.VariationsStackandQueue;

import adt.queue.Queue;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.stack.StackDoubleLinkedListImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueWithTwoStack<T> implements Queue<T> {

	StackDoubleLinkedListImpl<T> stack;
	StackDoubleLinkedListImpl<T> aux;

	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		QueueWithTwoStack<Integer> queue = new QueueWithTwoStack<Integer>(5);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		System.out.println(queue.head());
	}

	public QueueWithTwoStack(int size) {

		this.stack = new StackDoubleLinkedListImpl<T>(size);
		this.aux = new StackDoubleLinkedListImpl<T>(size);

	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!isFull()) {
			try {
				stack.push(element);
			} catch (StackOverflowException e) {

				e.printStackTrace();
			}

		}else {
			throw new QueueOverflowException();
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (!isEmpty()) {

			while (!stack.isEmpty()) {
				try {
					aux.push(stack.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			T element = null;
			
			try {
				element = aux.pop();
			} catch (StackUnderflowException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (!aux.isEmpty()) {
				try {
					stack.push(aux.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}

			}

			return element;
		} else {
			throw new QueueUnderflowException();

		}

	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty()) {

			while (!stack.isEmpty()) {
				try {
					aux.push(stack.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			element = aux.top();

			while (!aux.isEmpty()) {
				try {
					stack.push(aux.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}

			}

		}

		return element;
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack.isFull();
	}

}
