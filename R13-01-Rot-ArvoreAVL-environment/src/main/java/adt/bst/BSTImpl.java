package adt.bst;

import java.util.Arrays;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	public static void main(String[] args) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.insert(6);
		tree.insert(4);
		tree.insert(7);
		tree.insert(3);
		tree.insert(5);
		tree.insert(8);
		tree.remove(7);

	}

	// Devolve null se key não tem piso nesta BST.

	public T floor(T key) {
		BSTNode<T> x = floor(root, key);
		return x.getData();
	}

	// Devolve o nó que contém o piso de key
	// na subárvore com raiz x.
	// Devolve null se esse piso não existe.

	private BSTNode<T> ceil(BSTNode<T> x, T key) {
		BSTNode<T> ceil = new BSTNode<T>();

		if (!x.isEmpty()) {
			int cmp = key.compareTo(x.getData());
			if (cmp == 0) {
				ceil = x;
			} else if (cmp < 0) {
				BSTNode<T> t = ceil((BSTNode<T>) x.getLeft(), key);

				if (!t.isEmpty()) {
					ceil = t;
				}

				else {
					ceil = x;
				}

			} else {
				ceil = ceil((BSTNode<T>) x.getRight(), key);

			}

		}
		return ceil;

	}

	public T ceil(T key) {
		BSTNode<T> x = ceil(root, key);
		return x.getData();
	}

	private BSTNode<T> floor(BSTNode<T> x, T key) {
		BSTNode<T> floor = new BSTNode<T>();

		if (!x.isEmpty()) {
			int cmp = key.compareTo(x.getData());
			if (cmp == 0) {
				floor = x;
			} else if (cmp < 0) {
				floor = floor((BSTNode<T>) x.getLeft(), key);

			} else {
				BSTNode<T> t = floor((BSTNode<T>) x.getRight(), key);
				if (!t.isEmpty()) {
					floor = t;
				}

				else {
					floor = x;
				}

			}

		}
		return floor;

	}

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int height(BSTNode<T> node) {
		int result = -1;

		if (!node.isEmpty()) {
			result = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> aux = root;
		return search(element, aux);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();

		if (element != null && !node.isEmpty()) {
			if (node.getData().equals(element)) {
				result = node;

			} else if (node.getData().compareTo(element) < 0) {
				result = search(element, (BSTNode<T>) node.getRight());

			} else {
				result = search(element, (BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(root, element);
		}
	}

	@SuppressWarnings("unchecked")
	public void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setRight(new BSTNode<>());
			node.setLeft(new BSTNode<>());
			if (node.getParent() == null) {
				this.root = node;
			}
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}

	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				result = (BSTNode<T>) node;
			} else {
				result = maximum((BSTNode<T>) node.getRight());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	public BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				result = (BSTNode<T>) node;
			} else {
				result = minimum((BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {

		BSTNode<T> node = search(element);
		// nao tem sucessor
		if (node.isEmpty())
			return null;

		if (!node.getRight().isEmpty()) {

			// o sucessor e o menor elemento a direita.
			return minimum((BSTNode<T>) node.getRight());
		} else {
			// procurando o primeiro maior ascendente
			BSTNode<T> y = (BSTNode<T>) node.getParent();

			// usando o pai preciso ver se nao e nulo.
			while (y != null && node.equals(y.getRight())) {
				node = y;
				y = (BSTNode<T>) node.getParent();
			}
			return y;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);

		if (aux.isEmpty())
			return null;

		if (!aux.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) aux.getLeft());
		} else {
			BSTNode<T> y = (BSTNode<T>) aux.getParent();

			while (y != null && aux.equals(y.getLeft())) {
				aux = y;
				y = (BSTNode<T>) aux.getParent();
			}
			return y;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty()) {
				remove(node);
			}

		}
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {

			// folha
			if (node.isLeaf()) {
				node.setData(null);
			}

			// 1 filho
			else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if (node.getParent() != null) {
					// o no e filho a direita
					if (!node.getParent().getLeft().equals(node)) {
						// o no tem filho a esquerda
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
							// o no tem filho a direita
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
						// o no e filho a esquerda
					} else {
						// o no tem filho a esquerda
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());

							// o no tem filho a direita
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				// Remover a raiz.
				} else {
					if (node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
					} else {
						root = (BSTNode<T>) node.getLeft();
					}
					root.setParent(null);
				}

				// 2 filhos
			} else {
				T suc = sucessor(node.getData()).getData();
				remove(suc);
				node.setData(suc);

			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		preOrder(array, root, 0);
		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int index) {
		array[index++] = node.getData();

		if (!node.getLeft().isEmpty())
			index = preOrder(array, (BSTNode<T>) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = preOrder(array, (BSTNode<T>) node.getRight(), index);

		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		order(array, root, 0);
		return array;
	}

	private int order(T[] array, BSTNode<T> node, int index) {
		if (!node.getLeft().isEmpty()) {
			index = order(array, (BSTNode<T>) node.getLeft(), index);
		}

		array[index++] = node.getData();
		if (!node.getRight().isEmpty()) {
			index = order(array, (BSTNode<T>) node.getRight(), index);
		}

		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		postOrder(array, root, 0);
		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.getLeft().isEmpty())
			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = postOrder(array, (BSTNode<T>) node.getRight(), index);

		array[index++] = node.getData();

		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}