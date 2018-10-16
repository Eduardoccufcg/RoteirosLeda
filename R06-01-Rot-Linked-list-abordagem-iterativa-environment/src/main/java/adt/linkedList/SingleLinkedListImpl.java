package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
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

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
		// se o elemento do head ja for o elemento,o elemento next vira o head.
		if (this.head.getData().equals(element)) {
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
