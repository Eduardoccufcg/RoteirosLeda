package adt.bst;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import adt.bt.BT;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	public static void main(String[] args) {
		BSTImpl<Integer> bst = new BSTImpl<>();
		BSTImpl<Integer> bst2 = new BSTImpl<>();
		bst.insert(5);
		bst.insert(4);
		bst.insert(7);
		bst2.insert(5);
		bst2.insert(4);
		bst2.insert(7);
		bst.insert(8);
		System.out.println(bst.isBST(bst2));
		
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

	public boolean isEqualsBst(BSTImpl<T> l) {
		return isEqualsBst(l.getRoot(), this.root);
	}

	public boolean isEqualsBst(BSTNode<T> node1, BSTNode<T> node2) {
		boolean toReturn = false;
		if (node1.isEmpty() && node2.isEmpty()) {
			toReturn = true;
		} else if (node1.getData().equals(node2.getData())) {
			toReturn = isEqualsBst((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& isEqualsBst((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return toReturn;
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

	private BSTNode<T> minimum(BSTNode<T> node) {
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
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
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

	public T[] levelOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		levelOrder(array, root, 0);
		return array;
	}

	public T[] levelOrderRL() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		levelOrderRL(array, root, 0);
		return array;
	}

	private void levelOrder(T[] array, BSTNode<T> node, int i) {
		Deque<BSTNode<T>> fila = new ArrayDeque<>();
		fila.add(node);
		while (!fila.isEmpty()) {
			BSTNode<T> atual = fila.removeFirst();
			array[i++] = atual.getData();
			if (!atual.getLeft().isEmpty()) {
				fila.add((BSTNode<T>) atual.getLeft());
			}
			if (!atual.getRight().isEmpty()) {
				fila.add((BSTNode<T>) atual.getRight());
			}
		}

	}

	private void levelOrderRL(T[] array, BSTNode<T> node, int i) {
		Deque<BSTNode<T>> fila = new ArrayDeque<>();
		fila.add(node);
		while (!fila.isEmpty()) {
			BSTNode<T> atual = fila.removeFirst();
			array[i++] = atual.getData();

			if (!atual.getRight().isEmpty()) {
				fila.add((BSTNode<T>) atual.getRight());
			}
			if (!atual.getLeft().isEmpty()) {
				fila.add((BSTNode<T>) atual.getLeft());
			}
		}

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

	public T[] descendingOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return array;
		descendingOrder(array, root, 0);
		return array;
	}

	private int descendingOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.getRight().isEmpty()) {
			index = descendingOrder(array, (BSTNode<T>) node.getRight(), index);
		}

		array[index++] = node.getData();
		if (!node.getLeft().isEmpty()) {
			index = descendingOrder(array, (BSTNode<T>) node.getLeft(), index);
		}

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

	// calcula a quantidade de folhas
	public int quantFolhas() {
		int toReturn = 0;
		if (!this.isEmpty()) {
			toReturn = quantFolhas(this.getRoot());
		}
		return toReturn;
	}

	private int quantFolhas(BSTNode<T> node) {
		int result = 0;

		if (node.isLeaf()) {
			result = 1;
		} else {
			result = quantFolhas((BSTNode<T>) node.getLeft()) + quantFolhas((BSTNode<T>) node.getRight());
		}

		return result;
	}

	public double media() {
		double result = 0;
		if (!isEmpty()) {
			result = soma(root) / size();
		}
		return result;

	}

	private double soma(BSTNode<T> node) {
		double result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = visit(node) + soma((BSTNode<T>) node.getLeft()) + soma((BSTNode<T>) node.getRight());
		}
		return result;

	}

	private Integer visit(BSTNode<T> node) {

		return (Integer) node.getData();
	}
	
	
	public boolean isBST(BSTImpl<T> arvore) {
		return isBST(arvore.getRoot());
	}
	
	private boolean isBST(BSTNode<T> tree) {
		if(tree.isLeaf()) {
			return true;
		}else {
			boolean var = true;
			if(tree.getLeft().getData().compareTo(tree.getData())> 0) {
	          var = false;
			}
			if(tree.getRight().getData().compareTo(tree.getData()) < 0) {
				var = false;
			}
			return var && isBST((BSTNode<T>) tree.getLeft()) && isBST((BSTNode<T>) tree.getRight());
		}
	}
}
