package adt.bst;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The node of a binary tree. Its internal data has type T, which does not need
 * to be comparable.
 */
public class DNode<Object> {

	public static void main(String[] args) {
		Stack<DNode<String>> pilha = new Stack<DNode<String>>();
		DNode<String> p = null;
		DNode<String> root = null;
		String[] a = "(((3+6)*(3/3))*((4/2)*(4+6))".split("");
		System.out.print(Arrays.toString(a));
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals("(")) {
				if (i == 0) {
					root = new DNode<String>("#", new DNode<String>(), new DNode<String>(), null);
					DNode<String> b = root;
					pilha.push(b);
					p = root.getLeft();
				} else {
					p.setData("#");
					p.setLeft(new DNode<String>());
					p.setRight(new DNode<String>());
					pilha.push(p);
					p = p.getLeft();
				}

			} else if (isAlgarismo(a[i])) {
				p.setData(a[i]);
				p.setLeft(new DNode<String>());
				p.setRight(new DNode<String>());
			} else if (isOperador(a[i])) {
				DNode<String> k = (DNode<String>) pilha.peek();
				k.setData(a[i]);
				p = k.getRight();

			} else if (a[i].equals(")")) {
				try {
					pilha.pop();
				}catch(EmptyStackException  e) {
					System.out.println("Expressao invalida");
				}
				
			}
		}
		System.out.print(avaliaExpressao(root));

	}

	public static double avaliaExpressao(DNode<String> node) {

		if (node.isLeaf()) {
			return Double.parseDouble(node.getData());
		} else {
			double k = avaliaExpressao(node.getLeft());
			double j = avaliaExpressao(node.getRight());
			return avalia(k, j, node.getData());

		}

	}

	public static double avalia(double valor1, double valor2, String op) {
		Double resp;
		switch (op) {

		case "+":
			resp = valor1 + valor2;
			break;
		case "-":
			resp = valor1 - valor2;
			break;
		case "/":
			resp = valor1 / valor2;
			break;
		default:
			resp = valor1 * valor2;
			break;
		}
		return resp;

	}

	public static String[] postOrder(DNode<String> node) {
		String[] array = new String[11];
		if (node.isEmpty())
			return array;
		postOrder(array, node, 0);
		return array;
	}

	private static int postOrder(String[] array, DNode<String> node, int index) {
		if (!node.getLeft().isEmpty())
			index = postOrder(array, (DNode<String>) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = postOrder(array, (DNode<String>) node.getRight(), index);

		array[index++] = node.getData();

		return index;
	}

	private static boolean isOperador(String s) {
		return (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-"));
	}

	private static boolean isAlgarismo(String s) {
		return !(isOperador(s) || s.equals("(") || s.equals(")"));
	}

	protected Object data;
	protected DNode<Object> left;
	protected DNode<Object> right;
	protected DNode<Object> parent;

	public DNode(Object data, DNode<Object> left, DNode<Object> right, DNode<Object> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public DNode() {
	}

	public boolean isEmpty() {
		return this.data == null;
	}

	public boolean isLeaf() {
		return this.data != null && this.left.isEmpty() && this.right.isEmpty();
	}

	@Override
	public String toString() {
		String resp = "NIL";
		if (!isEmpty()) {
			resp = data.toString();
		}
		return resp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public DNode<Object> getLeft() {
		return left;
	}

	public void setLeft(DNode<Object> left) {
		this.left = left;
	}

	public DNode<Object> getRight() {
		return right;
	}

	public void setRight(DNode<Object> right) {
		this.right = right;
	}

}