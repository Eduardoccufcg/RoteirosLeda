package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {

		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;

	}

	public static void main(String[] args) {
		DoubleLinkedListImpl<Integer> fila = new DoubleLinkedListImpl<Integer>();
		fila.insertOrdenado(15);

		fila.insertOrdenado(20);
		fila.insertOrdenado(17);
		fila.insertOrdenado(21);
		fila.insertOrdenado(18);
		fila.insertOrdenado(14);
		System.out.println(Arrays.toString(fila.toArray()));
		fila.reverse();
		System.out.println(Arrays.toString(fila.toArray()));

	}

	@Override
	public void insertOrdenado(T element) {
		if (element != null) {
			// pego a referencia do head
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			// crio o no vazio
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			// coloco o elemento
			newNode.setData(element);
			if (isEmpty() || (newNode).compareTo(auxHead.getData()) > 0) {

				newNode.setNext(auxHead);
				// o previous desse novo elemento e NIL
				newNode.setPrevious(new DoubleLinkedListNode<T>());
				// O previous do head atual e esse novo elemento
				((DoubleLinkedListNode<T>) auxHead).setPrevious(newNode);

				if (auxHead.isNIL()) {
					// head e last no mesmo lugar
					setLast(newNode);
				}
				// o novo elemento sera o head
				setHead(newNode);

			} else {
				while (!auxHead.next.isNIL() && !((newNode).compareTo(auxHead.next.getData()) > 0)) {
					auxHead = (DoubleLinkedListNode<T>) auxHead.next;
				}
				if (auxHead.next.isNIL()) {
					last = newNode;
				}
				newNode.setNext(auxHead.getNext());
				newNode.setPrevious(auxHead);
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(newNode);
				auxHead.setNext(newNode);

			}

		}

	}

	@Override
	public void reverse() {

		DoubleLinkedListNode<T> left = (DoubleLinkedListNode<T>) this.head;
		DoubleLinkedListNode<T> right = this.last;
		int i = 0;
		int j = this.size() - 1; // (-)(n)
		while (i < j) {
			T aux = left.getData();
			left.setData(right.getData());
			right.setData(aux);
			i++;
			j--;
			left = (DoubleLinkedListNode<T>) left.getNext();
			right = right.getPrevious();
		}

	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead;

		// crio o no vazio
		newHead = new DoubleLinkedListNode<T>();
		// coloco o elemento
		newHead.setData(element);
		// o next desse novo elemento e o head atual
		newHead.setNext(head);
		// o previous desse novo elemento e NIL
		newHead.setPrevious(new DoubleLinkedListNode<T>());
		// O previous do head atual e esse novo elemento
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
			this.removeFirst();
		} else {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			}
			if (!auxHead.isNIL()) {
				auxHead.getPrevious().setNext(auxHead.getNext());
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(auxHead.getPrevious());
			}
		}
	}

}
