package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int max = max(array, leftIndex, rightIndex);

		Integer[] contador = new Integer[max + 1];
		Integer[] saida = new Integer[array.length];

		// 1ª - (Inicializar com zero)
		for (int i = 0; i < contador.length; i++) {
			contador[i] = 0;
		}

		// 2ª - (Contagem dos elementos)
		for (int i = leftIndex; i <= rightIndex; i++) {

			contador[array[i]]++;

		}

		// 3 ª (Soma de prefixos)
		for (int i = 1; i < contador.length; i++) {
			contador[i] += contador[i - 1];

		}

		// 4 ª (Ordenacao)
		for (int j = rightIndex; j >= leftIndex; j--) {

			saida[contador[array[j]]-- - 1] = array[j];

		}
		// copia pro array.
				int indice = 0;
				for (int i = leftIndex; i <= rightIndex; i++) {
					array[i] = saida[indice];
					indice++;
				}

	}

	public int max(Integer[] array, int leftIndex, int rightIndex) {
		int maior = 0;

		for (int i = leftIndex; i <= rightIndex; i++) {

			if (array[i] > maior) {

				maior = array[i];

			}

		}
		return maior;
	}
}
