package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int k = leftIndex; k < rightIndex; k++) {

			int minIndex = k; 
			// seleciona o elemento minimo
			for (int i = k + 1; i <= rightIndex; i++) {
				if (array[i].compareTo(array[minIndex]) < 0) {
					minIndex = i;

				}

			}
			// Coloca o elemenro mínimo na k-ésima posição
			Util.swap(array, minIndex, k);

		}
	}
}
