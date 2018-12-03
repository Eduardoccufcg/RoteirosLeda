package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public static void main(String[] args) {
		RBTreeImpl<Integer> myRB = new RBTreeImpl<Integer>();

		myRB.insert(41);
		myRB.insert(74);
		myRB.insert(31);
		myRB.insert(47);
		myRB.insert(19);
		myRB.insert(8);
		myRB.insert(32);
		myRB.insert(37);

		System.out.println(myRB.sizeBlack());
		System.out.println(myRB.sizeRed());

	}

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);
	}

	private int blackHeight(RBNode<T> node) {
		int result = -1;

		if (!node.isEmpty() && node.getColour().equals(Colour.BLACK)) {
			result = 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
		} else if (!node.isEmpty() && node.getColour().equals(Colour.RED)) {
			result = Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
		}
		return result;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) root, true);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node, boolean b) {
		boolean output = b;

		if (!node.isEmpty()) {
			if (((RBNode<T>) node).getColour() == Colour.RED) {
				// verifica se o filho a esquerdasafisfaz o invariante
				if (!node.getLeft().isEmpty() && ((RBNode<T>) node.getLeft()).getColour() == Colour.RED) {
					output = false;
				}
				// verifica se o filho a direita safisfaz o invariante
				if (!node.getRight().isEmpty() && ((RBNode<T>) node.getRight()).getColour() == Colour.RED) {
					output = false;
				}
			}
			// verifica os invariantes dos filhos dos filhos.
			return (verifyChildrenOfRedNodes((RBNode<T>) node.getLeft(), output)
					&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight(), output));
		} else {
			// Se o no e vazio entao retorno
			return output;
		}

	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) root, true);
	}

	private boolean verifyBlackHeight(RBNode<T> node, boolean b) {
		boolean output = b;

		if (!node.isEmpty()) {
			if (blackHeight((RBNode<T>) node.getLeft()) != blackHeight((RBNode<T>) node.getRight())) {
				output = false;
			}
			return (verifyBlackHeight((RBNode<T>) node.getLeft(), output)
					&& verifyBlackHeight((RBNode<T>) node.getRight(), output));
		} else {
			return output;
		}
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			insertAux((RBNode<T>) root, value);
		}
	}

	public void insertAux(RBNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(new RBNode<>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<>());
			node.getRight().setParent(node);

			node.setColour(Colour.RED);
			fixUpCase1(node);

		} else {
			if (element.compareTo(node.getData()) > 0) {
				insertAux((RBNode<T>) node.getRight(), element);
			} else if (element.compareTo(node.getData()) < 0) {
				insertAux((RBNode<T>) node.getLeft(), element);
			}
		}

	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		@SuppressWarnings("unchecked")
		RBNode<T>[] array = new RBNode[this.size()];
		rbPreOrder(array, (RBNode<T>) root, 0);
		return array;

	}

	private int rbPreOrder(RBNode<T>[] array, RBNode<T> node, int index) {
		array[index++] = (RBNode<T>) node;

		if (!node.getLeft().isEmpty())
			index = rbPreOrder(array, (RBNode<T>) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = rbPreOrder(array, (RBNode<T>) node.getRight(), index);

		return index;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.getParent() == null) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (!(((RBNode<T>) node.getParent()).getColour() == Colour.BLACK)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> grandfather = (RBNode<T>) node.getParent().getParent();
		RBNode<T> uncle = (RBNode<T>) grandfather.getRight();
		if (uncle.equals(node.getParent())) {
			uncle = (RBNode<T>) grandfather.getLeft();
		}

		if (uncle.getColour() == Colour.RED) {
			((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandfather.setColour(Colour.RED);
			fixUpCase1(grandfather);
		} else {
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> father = (RBNode<T>) node.getParent();
		RBNode<T> grandfather = (RBNode<T>) father.getParent();
		if (next.equals(father.getRight()) && father.equals(grandfather.getLeft())) {
			Util.leftRotation(father);
			next = (RBNode<T>) node.getLeft();
		} else if (next.equals(father.getLeft()) && father.equals(grandfather.getRight())) {
			Util.rightRotation(father);
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);

	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> father = (RBNode<T>) node.getParent();
		RBNode<T> grandfather = (RBNode<T>) father.getParent();
		father.setColour(Colour.BLACK);
		grandfather.setColour(Colour.RED);
		if (node.equals(father.getLeft())) {
			Util.rightRotation(grandfather);
		} else {
			Util.leftRotation(grandfather);
		}
	}

	public int sizeBlack() {
		return sizeBlack(root);
	}

	private int sizeBlack(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			if (((RBNode<T>) node).getColour() == Colour.BLACK) {
				result = 1 + sizeBlack((BSTNode<T>) node.getLeft()) + sizeBlack((BSTNode<T>) node.getRight());
			} else {
				result = 0 + sizeBlack((BSTNode<T>) node.getLeft()) + sizeBlack((BSTNode<T>) node.getRight());
			}

		}
		return result;
	}

	public int sizeRed() {
		return sizeRed(root);
	}

	private int sizeRed(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			if (((RBNode<T>) node).getColour() == Colour.RED) {
				result = 1 + sizeRed((BSTNode<T>) node.getLeft()) + sizeRed((BSTNode<T>) node.getRight());
			} else {
				result = 0 + sizeRed((BSTNode<T>) node.getLeft()) + sizeRed((BSTNode<T>) node.getRight());
			}

		}
		return result;
	}

}
