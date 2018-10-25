package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {

		super();
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;

	}

	public static void main(String[] args) {
		DoubleLinkedListImpl<Integer> fila = new DoubleLinkedListImpl<Integer>();
		fila.insertOrdenado(15);

		fila.insertOrdenado(21);
		fila.insertOrdenado(17);
		fila.insertOrdenado(21);
		fila.insertOrdenado(18);
		fila.insertOrdenado(14);
		System.out.println(Arrays.toString(fila.toArray()));
		fila.reverse();
		System.out.println(Arrays.toString(fila.toArray()));

	}

	public void insertOrdenado(T element) {
		if (element != null) {
			// pego a referencia do head
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			// crio o no vazio
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			// coloco o elemento
			newNode.setData(element);
			// Se a lista estiver vazia ou o elemento a ser adicionado for menor que o
			// primeiro, entao o elemento sera colocado no inicio
			if (isEmpty() || (newNode).compareTo(auxHead.getData()) < 0) {

				// Pode ser substituido pelo metodo insertFirst
				// o proximo desse novo no sera o head atual.
				// [elem] ---> [NIL]
				newNode.setNext(auxHead);
				// o previous desse novo elemento e NIL
				// [NIL] <-- [elem] ---> [NIL]
				newNode.setPrevious(new DoubleLinkedListNode<T>());
				// O previous do head atual e esse novo elemento
				// [NIL] <-- [elem] <---> [NIL]
				((DoubleLinkedListNode<T>) auxHead).setPrevious(newNode);

				if (auxHead.isNIL()) {
					// head e last no mesmo lugar
					setLast(newNode);
				}
				// o novo elemento sera o head
				setHead(newNode);

			} else {
				// enquanto o proximo nao for nil e nao for maior que o elemento a ser inserido.
				while (!auxHead.next.isNIL() && !((newNode).compareTo(auxHead.next.getData()) < 0)) {
					auxHead = (DoubleLinkedListNode<T>) auxHead.next;
				}
				// O auxHead estara uma posicao antes do elemento a ser inserido
				if (auxHead.next.isNIL()) {
					// Se o prox do auxHead for nil, entao o elemento sera inserido no final e sera
					// o ultimo.
					last = newNode;
				}
				// Antes [NOVO]
				// [elem] <---->[elem2]

				// [NOVO] -----> [elem2]
				newNode.setNext(auxHead.getNext());
				// [elem] <------[NOVO] -----> [elem2]
				newNode.setPrevious(auxHead);
				// [elem] <------[NOVO] <-----> [elem2]
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(newNode);
				// [elem] <------> [NOVO] <-----> [elem2]
				auxHead.setNext(newNode);

			}

		}

	}

	public void reverseRecursivo() {

		if (!head.isNIL()) {
			T data = head.getData();
			removeFirst();
			reverseRecursivo();
			insert(data);
		}

	}

	public void reverse() {

		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
		DoubleLinkedListNode<T> auxLast = this.last;

		while (!auxHead.equals(auxLast) && !auxHead.getNext().equals(auxLast)) {

			swap(auxHead, auxLast);
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		if (auxHead.getNext().equals(auxLast)) {
			swap(auxHead, auxLast);

		}

	}

	private void swap(DoubleLinkedListNode<T> a, DoubleLinkedListNode<T> b) {

		T aux = a.getData();
		a.setData(b.getData());
		b.setData(aux);
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead;

		// crio o novo no (element, next,previous)
		newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) head, new DoubleLinkedListNode<T>());

		((DoubleLinkedListNode<T>) head).setPrevious(newHead);

		if (head.isNIL()) {
			// head e last no mesmo lugar
			setLast(newHead);
		}
		// o novo elemento sera o head
		setHead(newHead);

	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			head = head.getNext();
			if (head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
			((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<T>());
		}

	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {
			last = last.getPrevious();
			if (last.isNIL()) {
				head = last;
			}
			last.setNext(new DoubleLinkedListNode<T>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	// insere no final
	public void insert(T element) {
		if (element != null) {

			DoubleLinkedListNode<T> newLast;

			newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), last);

			last.setNext(newLast);
			if (last.isNIL()) {

				head = newLast;
			}

			last = newLast;

		}

	}

	@Override
	public T search(T element) {
		T result = null;
		DoubleLinkedListNode<T> auxHead, auxLast;
		auxHead = (DoubleLinkedListNode<T>) head;
		auxLast = last;
		while (!auxHead.equals(auxLast) && !auxHead.getNext().equals(auxLast) && auxHead.getData() != element
				&& auxLast.getData() != element) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		if (auxHead.data == element) {
			result = auxHead.data;
		}
		if (auxLast.data == element) {
			result = auxLast.data;
		}

		return result;

	}

	@Override
	public void remove(T element) {
		if (element.equals(this.head.getData())) {
			this.removeFirst(); // se o item estiver na cabeca
		} else {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			}
			// se eu encontrei o elemento
			if (!auxHead.isNIL()) {
				auxHead.getPrevious().setNext(auxHead.getNext());
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(auxHead.getPrevious());
			}
		}
	}

}
