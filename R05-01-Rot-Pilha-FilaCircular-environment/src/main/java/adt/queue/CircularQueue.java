package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0;
		tail = 0;
		elements = 0;
	}

	public static void main(String args[]) {
		String a = "(()()()()())";
		String b = "(()()()()()";
		String c = "(((())";
		CircularQueue<Character> pilha = new CircularQueue<Character>(a.length());
		System.out.println(pilha.parenthesis(a));
		System.out.println(pilha.parenthesis(b));
		System.out.println(pilha.parenthesis(c));
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!isFull()) {
			if (element != null) {

				this.tail = (this.head + this.elements) % array.length;
				this.array[this.tail] = element;
				this.elements++;

			}
		} else {
			throw new QueueOverflowException();
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T elemento = array[this.head];
		this.head = (this.head + 1) % array.length;

		elements--;
		return elemento;
	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty()) {
			element = array[head];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return (elements == 0);
	}

	@Override
	public boolean isFull() {
		return (elements == array.length);
	}

	private boolean parenthesis(String a) {
		CircularQueue<Character> pilha = new CircularQueue<Character>(a.length());
		for (int i = 0; i < a.length(); i++) {
			if(a.charAt(i) == '('){
				try {
					pilha.enqueue(a.charAt(i));
				// nao é necessário
				} catch (QueueOverflowException e) {
				
				}
			}else if(a.charAt(i) == ')') {
				
					try {
						pilha.dequeue();
					} catch (QueueUnderflowException e) {
						return false;
					}
				
			}

		}
		if(!pilha.isEmpty()) {
			return false;
			
		}

		return true;

	}

}