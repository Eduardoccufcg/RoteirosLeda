package adt.linkedList;

import java.util.Arrays;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> lista = new SingleLinkedListImpl<Integer>();
		lista.insert(2);
		lista.insert(8);
		lista.insert(3);
		lista.insert(0);

		System.out.println(Arrays.toString(lista.toArray()));
		lista.reverseOther();
		System.out.println(Arrays.toString(lista.toArray()));
	}

	// assumindo que serao passados indices válidos.
	public void removeIndex(int indice) {
		SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>(); // o elemento antes do que eu quero remover
		if (indice == 0) {
			this.head = head.getNext();

		} else {
			int i = 1;
			SingleLinkedListNode<T> aux = this.head;
			while (i <= indice) {
				previous = aux;
				aux = aux.getNext();
				i++;
			}

			previous.setNext(aux.getNext());

		}

	}

	public int indexOf(T elem) {
		int indexOf = -1;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL() && !aux.getData().equals(elem)) {
				aux = aux.getNext();
				indexOf++;
			}
			if (aux.isNIL()) {
				indexOf = -1;
			} else {
				indexOf++;
			}

		}
		return indexOf;

	}

	public int lastIndexOf(T elem) {
		int index = -1;
		int lastIndexOf = -1;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				index++;
				if (aux.getData().equals(elem)) {
					lastIndexOf = index;
				}
				aux = aux.getNext();
			}

		}
		return lastIndexOf;

	}

	public void swap(T elem1, T elem2) {
		SingleLinkedListNode<T> e1 = null;
		SingleLinkedListNode<T> e2 = null;
		SingleLinkedListNode<T> aux = this.head;

		while (!aux.isNIL()) {
			if (aux.getData().equals(elem1)) {
				e1 = aux;

			} else if (aux.getData().equals(elem2)) {
				e2 = aux;

			}
			aux = aux.getNext();

		}

		if (e1 != null && e2 != null) {

			e1.setData(elem2);
			e2.setData(elem1);

		}
	}

	public T elementFromTheEnd(int k) {

		SingleLinkedListNode<T> aux = this.head;
		int j = 0;
		while (!aux.isNIL() && j < k) {

			aux = aux.getNext();
			j++;

		}
		SingleLinkedListNode<T> kth = this.head;
		while (!aux.isNIL()) {

			aux = aux.getNext();
			kth = kth.getNext();
			j++;

		}
		// k > que o tamanho do array.
		if (k > j) {
			return null;
		}
		return kth.getData();

	}

	private void reverseElem(SingleLinkedListNode<T> elem, SingleLinkedListNode<T> prev) {

		if (!elem.next.isNIL()) {
			reverseElem(elem.getNext(), elem);
		}
		elem.setNext(prev);

	}

	public void reverse() {
		// procuro o ultimo elemento da lista.
		SingleLinkedListNode<T> aux = this.head;
		SingleLinkedListNode<T> last = new SingleLinkedListNode<T>();
		while (!aux.isNIL()) {
			last = aux;
			aux = aux.next;
		}

		// troco os apontadores de proximo de cada elemento recursivamente.
		reverseElem(this.head, new SingleLinkedListNode<T>());
		// O head agora é p ultimoelemento da lista, ou seja o primeiro da lista
		// invertida.
		this.head = last;

	}

	// inverter a lista em uma nova abordagem, sem alterar os apontadores apenas
	// removendo os elementos e adicionando.
	public void reverseOther() {
		reversePasso(this.head);

	}

	private void reversePasso(SingleLinkedListNode<T> node) {
		if (!node.next.isNIL()) {
			T data = node.getData();
			removeFirst();
			reversePasso(this.head);
			insert(data);

		}

	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		boolean find = false;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL() && !find) {
			if (aux.getData().equals(element)) {
				result = aux.getData();
				find = true;
			} else {
				aux = aux.getNext();
			}

		}

		return result;

	}

	public void insertFirst(T element) {
		if (element != null) {

			SingleLinkedListNode<T> newHead;
			// Faco uma copia do head
			// se a lista esta vazia entao (Head)Elemento --> NIl

			// crio um elemento e seu proximo vai ser o head atual
			newHead = new SingleLinkedListNode<T>(element, head);
			// o head aponta agora para o elemento inserido
			head = newHead;
		}

	}

	public void removeFirst() {
		if (!isEmpty()) {
			head = head.next;

		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			SingleLinkedListNode<T> auxHead, newHead, newNode;
			// Faco uma copia do head
			auxHead = head;
			// se a lista esta vazia entao (Head)Elemento --> NIl
			if (isEmpty()) {
				// crio um elemento e seu proximo vai ser o head atual
				newHead = new SingleLinkedListNode<T>(element, head);
				// o head aponta agora para o elemento inserido
				head = newHead;
			} else {
				// procuro o elemento em que seu proximo e NIL.

				while (!auxHead.next.isNIL()) {
					auxHead = auxHead.next;
				}
				// elementos --> elemento inserido ----> NIL.
				newNode = new SingleLinkedListNode<>(element, auxHead.next);
				auxHead.next = newNode;
			}

		}

	}

	@SuppressWarnings("unchecked")
	public void insertOrdenado(T element) {
		if (element != null) {

			SingleLinkedListNode<T> auxHead, newHead, newNode;
			// Faco uma copia do head
			auxHead = head;
			newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			if (isEmpty() || (newNode).compareTo(auxHead.getData()) > 0) {
				// crio um elemento e seu proximo vai ser o head atual
				newHead = new SingleLinkedListNode<T>(element, head);
				// o head aponta agora para o elemento inserido
				head = newHead;
			} else {
				// procuro o elemento em que seu proximo e NIL.

				while (!auxHead.next.isNIL() && !((newNode).compareTo(auxHead.next.getData()) > 0)) {
					auxHead = auxHead.next;
				}
				// elementos --> elemento inserido ----> NIL.
				newNode = new SingleLinkedListNode<>(element, auxHead.next);
				auxHead.next = newNode;
			}

		}

	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
		// se o elemento do head ja for o elemento,o elemento next vira o head.
		if (element.equals(this.head.getData())) {
			this.head = head.getNext();
		} else {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL() && !aux.getData().equals(element)) {
				previous = aux;
				aux = aux.getNext();
			}
			// se o aux nao e NIL entao o elemento a ser removido esta entre dois elementos
			if (!aux.isNIL()) {
				previous.setNext(aux.getNext());
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		int count = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			array[count++] = aux.getData();
			aux = aux.getNext();
		}
		return array;

	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
