package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty()) {
			element = array[0];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return (this.tail == -1);

	}

	@Override
	public boolean isFull() {
		return (this.tail == array.length - 1);
	}

	private void shiftLeft() {
		for(int  i =0; i < tail;i++) {
			array[i] = array[i+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!isFull()) {
			if(element != null) {
				array[++tail] = element;
			}
			
			
		}else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if(!isEmpty()) {
			result = array[0];
			shiftLeft();
			tail--;

		}else {
			throw new QueueUnderflowException();
		}
		return result;

	}

}