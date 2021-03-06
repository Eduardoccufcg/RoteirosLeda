package adt.linkedList;

import java.util.Arrays;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T>, Comparable {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public static void main(String[] args) {
		RecursiveSingleLinkedListImpl<Integer> lista = new RecursiveSingleLinkedListImpl<Integer>();
		lista.insert(10);
		lista.insert(8888);
		lista.insert(4);
		lista.insert(1);
		lista.reverse();
		System.out.println(Arrays.toString(lista.toArray()));
		

		System.out.println((lista.maior()));

	}

	public void swap(T elem1, T elem2) {

	}

	public void reverse() {
		if (!this.isEmpty()) {
			T data = this.data;
			this.remove(data);
			this.reverse();
			this.insert(data);
		}

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {

		return (data == null);

	}

	@Override
	public int size() {
		int tamanho;
		if (isEmpty()) {
			tamanho = 0;
		} else {
			tamanho = 1 + next.size();
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		T saida = null;
		if (!isEmpty()) {
			if (this.getData().equals(element)) {
				saida = this.getData();
			} else {
				saida = this.getNext().search(element);
			}
		}
		return saida;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			if (isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			}

			else {
				this.getNext().insert(element);
			}

		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		auxArray(result, this, 0);
		return result;
	}

	public void auxArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int i) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			auxArray(array, node.getNext(), ++i);

		}

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

	public T maior() {
		T maior = this.getData();

		if (this.next != null && !this.next.isEmpty()) {
			maior = this.getData();
			T outroMaior = next.maior();
			maior = max(maior, outroMaior);

		}
		return maior;

	}

	private T max(T maior, T outroMaior) {
		if ((Integer) maior > (Integer) outroMaior) {
			return maior;
		} else if ((Integer) outroMaior > (Integer) maior) {
			return outroMaior;
		} else {
			return maior;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object o) {

		if ((Integer) ((RecursiveSingleLinkedListImpl<T>) o).getData() < (Integer) this.getData()) {
			return 1;
		} else if ((Integer) ((RecursiveSingleLinkedListImpl<T>) o).getData() > (Integer) this.getData()) {
			return -1;
		} else {
			return 0;
		}
	}

}
