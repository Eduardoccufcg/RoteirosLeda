package adt.avltree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public static void main(String[] args) {
		AVLCountAndFillImpl<Integer> a = new AVLCountAndFillImpl<Integer>();
		a.insert(99);

		a.insert(44);
		a.insert(71);
		a.insert(80);
		a.insert(74);
		a.insert(63);
		a.insert(59);
		System.out.println(a.RRcounter);
		System.out.println(a.LLcounter);
		System.out.println(a.RLcounter);
		System.out.println(a.LRcounter);
	}

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

		if (!node.isEmpty() && !(Math.abs(balance) <= 1)) {

			// no pesa pra esquerda
			if (balance > 0) {
				int balanceLeft = this.calculateBalance((BSTNode<T>) node.getLeft());
				// filho a esquerda pesa pra direita
				if (balanceLeft < 0) {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					LRcounter++;
				} else {
					LLcounter++;
				}
				Util.rightRotation(node);

			} else {
				// no pesa pra direita
				int balanceRight = this.calculateBalance((BSTNode<T>) node.getRight());
				// filho a direita pesa pra esquerda
				if (balanceRight > 0) {

					Util.rightRotation((BSTNode<T>) node.getRight());
					RLcounter++;
				} else {
					RRcounter++;
				}
				Util.leftRotation(node);

			}
			if (this.getRoot().equals(node)) {
				this.root = (BSTNode<T>) node.getParent();
			}

		}

	}

	@Override
	public void fillWithoutRebalance(T[] array) {

		Deque<T[]> fila = new ArrayDeque<>();
		Arrays.sort(array);
		fila.add(array);
		while (!fila.isEmpty()) {
			T[] arrayAux = fila.removeFirst();
			int middle = arrayAux.length / 2;
			T[] arrayAux1 = Arrays.copyOfRange(arrayAux, 0, middle);
			T[] arrayAux2 = Arrays.copyOfRange(arrayAux, middle + 1, arrayAux.length);
			if (arrayAux.length > 1) {
				fila.add(arrayAux1);
				fila.add(arrayAux2);
			}
			insert(arrayAux[middle]);
		}

	}

}
