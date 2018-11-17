package sorting;

import sorting.AbstractSorting;
import util.Util;

public class OddEvenBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean isSort = false;

		while (!isSort) {

			isSort = true;

			for (int i = leftIndex; i < rightIndex; i = i + 2) {
				if (array[i].compareTo(array[i + 1]) > 0) {

					Util.swap(array, i, i + 1);
					isSort = false;

				}

			}

			for (int j = leftIndex + 1; j < rightIndex; j = j + 2) {
				if (array[j].compareTo(array[j + 1]) > 0) {

					Util.swap(array, j, j + 1);
					isSort = false;
				}
			}

		}

	}

}
