package adt.hashtable.open;

import java.util.Arrays;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;
import util.Util;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	private double loadFactor;
	private int threshold;

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
		loadFactor = 0.50;
		threshold = (int) (size * loadFactor);
	}

	public static void main(String[] args) {

		HashtableOpenAddressQuadraticProbingImpl<HashtableElement> tabela = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
				7, HashFunctionClosedAddressMethod.DIVISION, 3, 5);

		tabela.insert(new HashtableElement(34));
		tabela.insert(new HashtableElement(40));
		tabela.insert(new HashtableElement(11));
		tabela.insert(new HashtableElement(95));
		System.out.println(tabela.toString());
	}

	@Override
	public String toString() {
		return Arrays.toString(this.table);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if(elements + 1 > threshold) {
				rehash();
			}
			if (element != null && this.search(element) == null) {
				int i = 0;
				while (i < this.table.length) {
					int j = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, i);
					if (this.table[j] == null || (new DELETED()).equals(this.table[j])) {
						this.table[j] = element;
						this.elements++;
						return;
					} else {
						i++;
						this.COLLISIONS++;
					}
				}
				throw new HashtableOverflowException();
			}

		}
	}

	@Override
	public void remove(T element) {
		int i = 0;
		while (i < this.table.length) {
			int index = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, i);
			if (this.table[index] == null) {
				break;
			} else if (element.equals(this.table[index])) {
				this.table[index] = new DELETED();
				this.elements--;
			} else {
				i++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int i = 0;
		T elem = null;
		while (i < this.table.length) {
			int aux = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, i);
			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				elem = (T) this.table[aux];
				break;
			}
			i++;
		}
		return elem;
	}

	@Override
	public int indexOf(T element) {
		int i = 0;
		int saida = -1;
		while (i < this.table.length) {
			int aux = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, i);
			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				saida = aux;
				break;
			} else {
				i++;
			}
		}
		return saida;

	}

	@SuppressWarnings("unchecked")
	public void rehash() {

		int novaCapacidade = primeNumberNearTwoSize();
		((HashFunctionQuadraticProbing<T>) this.hashFunction).setTableCapacity(novaCapacidade);
		threshold = (int) (novaCapacidade * loadFactor);
		Storable[] aux = new Storable[novaCapacidade];
		

		for (int i = 0; i < this.table.length; i++) {
			aux[i] = (Storable) this.table[i];
		}
		this.table = new Storable[novaCapacidade];
	


		for (int i = 0; i < aux.length; i++) {

			if (!(aux[i] == null || (new DELETED()).equals(this.table[i]))) {
				insert((T) aux[i]);
			}
			
			

		}

	}

	private int primeNumberNearTwoSize() {
		int num = this.table.length * 2;
		int numAux = num;

		while (!Util.isPrime(num) && !Util.isPrime(numAux)) {
			num++;
			numAux--;

		}
		if (Util.isPrime(numAux)) {
			return numAux;
		} else {
			return num;
		}
	}
}
