package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if (height <= maxHeight) {
			SkipListNode<T>[] update = new SkipListNode[maxHeight];
			SkipListNode<T> x = root;
			for (int i = maxHeight - 1; i >= 0; i--) {
				while (x.forward[i].getKey() < key) {
					x = x.forward[i];

				}
				update[i] = x;
			}
			x = x.forward[0];
			if (x.key == key) {
				x.setValue(newValue);
			} else {
				int level = height;
				x = new SkipListNode<>(key, level, newValue);
				for (int i = 0; i < level; i++) {
					x.forward[i] = update[i].forward[i];
					update[i].forward[i] = x;

				}

			}

		} else {
			throw new RuntimeException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> x = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (x.forward[i].getKey() < key) {
				x = x.forward[i];

			}
			update[i] = x;
		}
		x = x.forward[0];
		if (x.key == key) {

			for (int i = 0; i < maxHeight && update[i].forward[i] == x; i++) {
				update[i].forward[i] = x.forward[i];
			}
		}

	}

	@Override
	public int height() {
		int i;
		for (i = maxHeight - 1; i >= 0 && root.forward[i] == NIL; i--)
			;

		return i + 1;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (x.forward[i].getKey() < key) {
				x = x.forward[i];

			}
		}
		x = x.forward[0];
		SkipListNode<T> node = null;
		if (x.getKey() == key) {
			node = x;
		}
		return node;

	}

	@Override
	public int size() {
		// varer a lista de baixo
		int size = -1;
		SkipListNode<T> node = root.forward[0];
		while (node != null) {
			node = node.forward[0];
			size++;
		}
		return size;

	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> node = root;
		// somo +2 para guardar o root e o NIL.
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		int i = 0;
		while (node != null) {
			array[i++] = node;
			node = node.forward[0];

		}
		return array;

	}

}
