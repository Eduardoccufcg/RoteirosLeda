package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {

		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;

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

}
