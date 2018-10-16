package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		while (leftIndex < rightIndex) {

			// levo o maior elemento para o final
			for (int i = leftIndex; i < rightIndex; i++) {

				if (array[i].compareTo(array[i + 1]) > 0) {
					Util.swap(array, i, i + 1);

				}

			}
			// declemento righIndex, pois o ultimo elemento está na sua posição.
			rightIndex--;

			// levo o menor elemento para o ínicio
			for (int j = rightIndex; j > leftIndex; j--) {

				if (array[j].compareTo(array[j - 1]) < 0) {
					Util.swap(array, j, j - 1);

				}

			}

			// inclemento leftIndex, pois o primeiro elemento está na sua posição.
			leftIndex++;

		}
	}
}
