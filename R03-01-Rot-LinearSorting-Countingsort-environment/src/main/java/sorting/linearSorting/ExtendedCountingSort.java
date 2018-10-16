package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		Integer maior = maior(array, leftIndex, rightIndex);
		Integer menor = menor(array, leftIndex, rightIndex);
		int[] contador = new int[maior - menor + 1];

		int[] resposta = new int[rightIndex - leftIndex + 1];

		// contar.

		for (int i = leftIndex; i <= rightIndex; i++) {
			contador[array[i] - menor]++;
		}

		// complemento.
		for (int j = 1; j < contador.length; j++) {
			contador[j] += contador[j - 1];
		}
		// ordena.

		for (int i = rightIndex; i >= leftIndex; i--) {
			resposta[contador[array[i] - menor]-- - 1] = array[i];
		}

		// copia pro array.
		int indice = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = resposta[indice];
			indice++;
		}

	}

	public int maior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = Integer.MIN_VALUE;

		for (int i = leftIndex; i <= rightIndex; i++) {

			if (array[i] > maior) {

				maior = array[i];

			}

		}
		return maior;
	}

	public int menor(Integer[] array, int leftIndex, int rightIndex) {
		int menor = Integer.MAX_VALUE;

		for (int i = leftIndex; i <= rightIndex; i++) {

			if (array[i] < menor) {

				menor = array[i];

			}

		}
		return menor;

	}


}
