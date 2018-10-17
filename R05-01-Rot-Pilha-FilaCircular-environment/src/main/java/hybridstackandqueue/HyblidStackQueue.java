package hybridstackandqueue;

import adt.queue.Queue;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.stack.Stack;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class HyblidStackQueue<T> implements Queue<T>, Stack<T> {

	// [ Queue | Stack]
	private T[] array;
	private int top; // topo da pilha
	private int tail; // fim da fila

	public HyblidStackQueue(int size) {

		this.array = (T[]) new Object[size];
		top = size;
		tail = -1;

	}

	public static void main(String[] args)
			throws StackOverflowException, StackUnderflowException, QueueOverflowException, QueueUnderflowException {
		HyblidStackQueue<Integer> pilhafila = new HyblidStackQueue<Integer>(4);
		/// Utilizando como pilha.
		System.out.println("push 10");
		pilhafila.push(10);
		System.out.println("push 20");
		pilhafila.push(20);
		System.out.println("push 7");
		pilhafila.push(7);
		System.out.println("push 8");
		pilhafila.push(8);
		System.out.println(pilhafila.top() + " topo");
		pilhafila.pop();
		System.out.println("pop");
		System.out.println(pilhafila.top() + " topo");
		pilhafila.pop();
		System.out.println("pop");
		System.out.println(pilhafila.top() + " topo");
		pilhafila.pop();
		System.out.println("pop");
		System.out.println(pilhafila.top() + " topo");
		pilhafila.pop(); // esvazie a estrutura.

		// utilizando como fila.
		System.out.println("pop");
		System.out.println();
		System.out.println("enfilerar 12");
		pilhafila.enqueue(12);
		System.out.println("enfilerar 7");
		pilhafila.enqueue(7);
		System.out.println("enfilerar 9");
		pilhafila.enqueue(9);
		System.out.println(pilhafila.head() + " head");
		pilhafila.dequeue();
		System.out.println("desenfilerar");
		System.out.println(pilhafila.head() + " head");
		pilhafila.dequeue();
		System.out.println("desenfilerar");
		System.out.println(pilhafila.head() + " head");
		pilhafila.dequeue();
		System.out.println("desenfilerar");

		System.out.println(pilhafila.head() + " head");
		
		// os dois ao mesmo tempo.
		pilhafila.push(8);
		pilhafila.push(7);
		pilhafila.enqueue(6);
		pilhafila.enqueue(4);
		pilhafila.pop();
		pilhafila.pop();
		pilhafila.dequeue();
		pilhafila.dequeue();
		pilhafila.push(89);
		pilhafila.push(752);
		pilhafila.enqueue(9);
		pilhafila.enqueue(7);
		System.out.println(pilhafila.top());

		System.out.println(pilhafila.head());
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			if (element != null) {
				this.top--;
				array[this.top] = element;
			}

		} else {
			throw new StackOverflowException();
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		// se as duas vazias ou somente a pilha
		if (isEmpty() || this.top == this.array.length) {
			throw new StackUnderflowException();
		}
		T element = array[this.top];
		top++;
		return element;
	}

	@Override
	public T top() {
		T element = null;
		if (!isEmpty()) {
			element = array[this.top];

		}

		return element;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!isFull()) {
			if (element != null) {
				array[++tail] = element;
			}

		} else {
			throw new QueueOverflowException();
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		// se as duas estao vazias ou apenas a fila.
		if (!isEmpty() || !(tail == -1)) {
			result = array[0];
			shiftLeft();
			tail--;

		} else {
			throw new QueueUnderflowException();
		}
		return result;
	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty() && tail != -1) {
			element = array[0];
		}
		return element;
	}

	// a pilha e a fila estao vazias.
	@Override
	public boolean isEmpty() {
		return (this.top == this.array.length && tail == -1);
	}

	@Override
	public boolean isFull() {
		return (Math.abs(tail - top) == 1);
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
	}

}
