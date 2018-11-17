package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);

		if (balance > 1) {
			int balanceLeft = this.calculateBalance((BSTNode<T>) node.getLeft());
			if (balanceLeft > 0) {
				Util.rightRotation(node);
				LLcounter++;
			} else if (balanceLeft <= 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				Util.rightRotation(node);
				LRcounter++;
			}

			if (this.getRoot().equals(node)) {
				this.root = (BSTNode<T>) node.getParent();
			}

		}
		if (balance < -1) {
			int balanceRight = this.calculateBalance((BSTNode<T>) node.getRight());
			if (balanceRight < 0) {
				Util.leftRotation(node);
				RRcounter++;
			} else if (balanceRight >= 0) {
				Util.rightRotation((BSTNode<T>) node.getRight());
				Util.leftRotation(node);
				RLcounter++;
			}

			if (this.getRoot().equals(node)) {
				this.root = (BSTNode<T>) node.getParent();
			}

		}

	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		List<T[]> list = new ArrayList<T[]>();

		Arrays.sort(array);
		list.add(array);
		int i = 0;
		while (i < list.size()) {
			T[] arrayAux = list.get(i);
			int middle = arrayAux.length / 2;
			T[] arrayAux1 = Arrays.copyOfRange(arrayAux, 0, middle);
			T[] arrayAux2 = Arrays.copyOfRange(arrayAux, middle + 1, arrayAux.length);
			if (arrayAux.length > 1) {
				list.add(arrayAux1);
				list.add(arrayAux2);
			}
			insert(arrayAux[middle]);
			i++;

		}

	}

}
