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
		

		System.out.println(Arrays.toString(fila.toArray()));
		fila.remove(2);
		System.out.println(Arrays.toString(fila.toArray()));

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

		newHead = new DoubleLinkedListNode<T>();
		newHead.setData(element);
		newHead.setNext(head);
		newHead.setPrevious(new DoubleLinkedListNode<T>());
		((DoubleLinkedListNode<T>) head).setPrevious(newHead);
		if (head.isNIL()) {
			setLast(newHead);
		}
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
			while(!auxHead.isNIL() && !auxHead.getData().equals(element)) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			}
			if (!auxHead.isNIL()) {
				auxHead.getPrevious().setNext(auxHead.getNext());
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(auxHead.getPrevious());
			}
		}
}
	
	
	
	
	
	
	
	
	
	

}
