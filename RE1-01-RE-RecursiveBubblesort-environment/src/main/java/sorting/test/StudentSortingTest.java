package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.RecursiveBubbleSort;



public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] array1;
	private Integer[] array2;
	private Integer[] array3;
	private Integer[] array4;
	private Integer[] array5;
	private Integer[] array6;
	private Integer[] array7;
	private Integer[] array8;
	private Integer[] array9;
	private Integer[] array10;
	private Integer[] array11;
	private Integer[] array12;
	private Integer[] array13;
	private Integer[] array14;
	private Integer[] array15;
	private Integer[] array16;
	private Integer[] array17;
	private Integer[] array18;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		array1 = new Integer[] { -1, 8, 5, 8, -2, 63, 3, 4, 3, 544, -3, 4, 7, 1, 2, 3, 8596, 54, 4, 468, 4, 4, 73, 36,
				324, 6554, 334, 335, 6332, 833, 58652, 855, 452, 453, 4523, 4523, 455, 5532, 86532, 85623, 552, 5656,
				8632, 85, 5, 5, 5, 8, 9, 3, 3, 6, 4, 45, 3, 9, 9, 3, 4, 6, 69, 6, 5, -5, -2, -7845, 565, 5656, 555, 88,
				84, -445, -2563, -5236 };
		array2 = new Integer[] { -1, 83, 5, 8, 2, 63, 3, -74, 3, 544, 3, 43 };
		array3 = new Integer[] { -1, 0, 5, 8, 2, 6, 3, 4, 3, 54, 3, 4, 100 };
		array4 = new Integer[] { -1, 83, 5, -8, 2, 63, -3, 4, 3, 5443, 3, -4 };
		array5 = new Integer[] { -1, 8, 5, 8, 12, 63, 3, 4, 3, 544, 3, 4 };
		array6 = new Integer[] { -1, 8, 5, -8, -12, 63, 3, 74, 3, 544, 3, 4 };
		array7 = new Integer[] { -1, 8, 5, 87, 2, 63, 3, 47, 3, -544, 3, 4 };
		array8 = new Integer[] { -1, 8, 5, -18, -2, 63, 3, 43, 3, 544, 3, 4 };
		array9 = new Integer[] { -17, 83, 57, 87, 2, 763, 3, 4, 3, 544, 73, 4 };
		array10 = new Integer[] { -1, 8, 75, 0, -127, 63, 3, 4, 73, 544, 73, 4 };
		array11 = new Integer[] { -1, 8, 5, 8, 2, 63, 3, 4, -3, 544, 3, 4 };
		array12 = new Integer[] { -1, 87, 5, 8, 2, 63, 33, 4, -3, 544, 3, -4 };
		array13 = new Integer[] { -1, 8, 5, 8, -2, 63, 3, 4, 3, 544, 3, 4 };
		array14 = new Integer[] { -71, 87, -5, 8, 2, 63, 3, 4, 3, 544, 3, -4 };
		array15 = new Integer[] { -1, 8, 5, 8, -2, 63, 3, 4, 3, 544, 3, 4 };
		array16 = new Integer[] { -71, 48, 15, 8, 2, 63, 3, 4, 3, -544, 3, 4, 8, -5, 3, 2, -1, -5, 25, -5, 52 };
		array17 = new Integer[] { -1, 8, 51, 8, -2, 63, 3, 4, -3, -544, 3, 14, 55, 3, 3, 5, 3, -12, -5, -545, -28, -9,
				5, 54, 4, 6 };
		array18 = new Integer[] { -1, 8, 15, 8, 2, 63, 3, 4, 3, -544, 3, 14, 78, 52, -65, 65, 45, 3, 6, 3, 6, 4, 24, 4,
				-5, 3, 2, -1, -25, -1, 1, 298, -4556, -5223, -4, -52, 454, 2526, 56 };

		getImplementation();
	}

	// // MÃTODOS AUXILIARES DA INICIALIZAÃÃO
	/**
	 * MÃ©todo que inicializa a implementaÃ§Ã£o a ser testada com a implementaÃ§Ã£o
	 * do aluno
	 */
	private void getImplementation() {
		
		this.implementation = new RecursiveBubbleSort<Integer>();
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// METODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if (array.length > 0) {
			copy1 = Arrays.copyOf(array, array.length);
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	// METODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÃÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÃO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÃTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
	public void genericTest(Integer[] array, int left, int right) {
		Integer[] copy1 = {};
		Integer[] copy2 = {};
		if (array.length > 0) {
			copy1 = Arrays.copyOfRange(array, left, right + 1);
		}
		implementation.sort(array, left, right);
		copy2 = Arrays.copyOfRange(array, left, right + 1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, copy2);
	}

	@Test
	public void testeSort6() {
		genericTest(vetorTamPar, 1, 5);
	}

	@Test
	public void testeSort7() {
		genericTest(vetorTamImpar, 1, 5);
	}

	@Test
	public void testeSort8() {
		genericTest(vetorValoresIguais, 1, 5);
	}

	@Test
	public void testeSort9() {
		genericTest(vetorValoresRepetidos, 2, 7);
	}

	@Test
	public void testeSort10() {
		genericTest(array1, 0, 5);
	}

	@Test
	public void testeSort11() {
		genericTest(array2, 0, 6);
	}

	@Test
	public void testeSort12() {
		genericTest(array3, 1, 2);
	}

	@Test
	public void testeSort13() {
		genericTest(array4, 1, 1);
	}

	@Test
	public void testeSort14() {
		genericTest(array5, 1, 4);
	}

	@Test
	public void testeSort15() {
		genericTest(array6, 0, 7);
	}

	@Test
	public void testeSort16() {
		genericTest(array7, 1, 4);
	}

	@Test
	public void testeSort17() {
		genericTest(array8, 0, array8.length - 1);
	}

	@Test
	public void testeSort18() {
		genericTest(array9, 3, 4);
	}

	@Test
	public void testeSort19() {
		genericTest(array10, 4, 5);
	}

	@Test
	public void testeSort20() {
		genericTest(array11, 2, 4);
	}

	@Test
	public void testeSort21() {
		genericTest(array12, 7, 8);
	}

	@Test
	public void testeSort22() {
		genericTest(array13, 4, 4);
	}

	@Test
	public void testeSort23() {
		genericTest(array14, 4, 5);
	}

	@Test
	public void testeSort24() {
		genericTest(array15, 3, 5);
	}

	@Test
	public void testeSort25() {
		genericTest(array1, 10, 15);
	}

	@Test
	public void testeSort26() {
		genericTest(array2, 7, 8);
	}

	@Test
	public void testeSort27() {
		genericTest(array3, 1, 2);
	}

	@Test
	public void testeSort28() {
		genericTest(array4, 1, 7);
	}

	@Test
	public void testeSort29() {
		genericTest(array5, 5, 6);
	}

	@Test
	public void testeSort30() {
		genericTest(array6, 3, 4);
	}

	@Test
	public void testeSort31() {
		genericTest(array7, 8, 9);
	}

	@Test
	public void testeSort32() {
		genericTest(array8, 4, 4);
	}

	@Test
	public void testeSort33() {
		genericTest(array9, 3, 4);
	}

	@Test
	public void testeSort34() {
		genericTest(array10, 7, 7);
	}

	@Test
	public void testeSort35() {
		genericTest(array11, 3, 3);
	}

	@Test
	public void testeSort36() {
		genericTest(array12, 6, 7);
	}

	@Test
	public void testeSort37() {
		genericTest(array13, 6, 6);
	}

	@Test
	public void testeSort38() {
		genericTest(array14, 6, 9);
	}

	@Test
	public void testeSort39() {
		genericTest(array15, 4, 4);
	}

	@Test
	public void testeSort40() {
		genericTest(array1, 7, 16);
	}

	@Test
	public void testeSort41() {
		genericTest(array2, 0, 1);
	}

	@Test
	public void testeSort42() {
		genericTest(array3, 1, 6);
	}

	@Test
	public void testeSort43() {
		genericTest(array4, 9, 9);
	}

	@Test
	public void testeSort44() {
		genericTest(array5, 6, 10);
	}

	@Test
	public void testeSort45() {
		genericTest(array6, 4, 7);
	}

	@Test
	public void testeSort46() {
		genericTest(array7, 0, 1);
	}

	@Test
	public void testeSort47() {
		genericTest(array8, 1, 8);
	}

	@Test
	public void testeSort49() {
		genericTest(array9, 4, 5);
	}

	@Test
	public void testeSort50() {
		genericTest(array10, 6, 7);
	}

	@Test
	public void testeSort51() {
		genericTest(array11, 2, 4);
	}

	@Test
	public void testeSort52() {
		genericTest(array12, 7, 8);
	}

	@Test
	public void testeSort53() {
		genericTest(array13, 5, 5);
	}

	@Test
	public void testeSort54() {
		genericTest(array14, 3, 7);
	}

	@Test
	public void testeSort55() {
		genericTest(array15, 1, 6);
	}

	@Test
	public void testeSort56() {
		genericTest(array1, 2, 3);
	}

	@Test
	public void testeSort57() {
		genericTest(array2, 7, 10);
	}

	@Test
	public void testeSort58() {
		genericTest(array3, 2, 2);
	}

	@Test
	public void testeSort59() {
		genericTest(array4, 5, 6);
	}

	@Test
	public void testeSort60() {
		genericTest(array5, 8, 8);
	}

	@Test
	public void testeSort61() {
		genericTest(array6, 0, 4);
	}

	@Test
	public void testeSort62() {
		genericTest(array7, 1, 7);
	}

	@Test
	public void testeSort63() {
		genericTest(array8, 1, 6);
	}

	@Test
	public void testeSort64() {
		genericTest(array9, 3, 7);
	}

	@Test
	public void testeSort65() {
		genericTest(array10, 4, 6);
	}

	@Test
	public void testeSort66() {
		genericTest(array11, 1, 7);
	}

	@Test
	public void testeSort67() {
		genericTest(array12, 2, 5);
	}

	@Test
	public void testeSort68() {
		genericTest(array13, 4, 5);
	}

	@Test
	public void testeSort69() {
		genericTest(array14, 4, 7);
	}

	@Test
	public void testeSort70() {
		genericTest(array15, 2, 3);
	}

	@Test
	public void testeSort71() {
		genericTest(array1, 1, 6);
	}

	@Test
	public void testeSort72() {
		genericTest(array2, 2, 8);
	}

	@Test
	public void testeSort73() {
		genericTest(array3, 0, 4);
	}

	@Test
	public void testeSort74() {
		genericTest(array4, 1, 1);
	}

	@Test
	public void testeSort75() {
		genericTest(array5, 1, 4);
	}

	@Test
	public void testeSort76() {
		genericTest(array6, 0, 7);
	}

	@Test
	public void testeSort77() {
		genericTest(array7, 1, 4);
	}

	@Test
	public void testeSort78() {
		genericTest(array8, 1, 5);
	}

	@Test
	public void testeSort79() {
		genericTest(array9, 3, 4);
	}

	@Test
	public void testeSort80() {
		genericTest(array10, 4, 5);
	}

	@Test
	public void testeSort81() {
		genericTest(array11, 2, 6);
	}

	@Test
	public void testeSort82() {
		genericTest(array12, 1, 2);
	}

	@Test
	public void testeSort83() {
		genericTest(array13, 7, 8);
	}

	@Test
	public void testeSort84() {
		genericTest(array14, 5, 6);
	}

	@Test
	public void testeSort85() {
		genericTest(array15, 3, 4);
	}

	@Test
	public void testeSort86() {
		genericTest(array1, 1, 6);
	}

	@Test
	public void testeSort87() {
		genericTest(array2, 5, 6);
	}

	@Test
	public void testeSort88() {
		genericTest(array3, 1, 3);
	}

	@Test
	public void testeSort89() {
		genericTest(array4, 0, 0);
	}

	@Test
	public void testeSort90() {
		genericTest(array5, 4, 5);
	}

	@Test
	public void testeSort91() {
		genericTest(array16, 2, 3);
	}

	@Test
	public void testeSort92() {
		genericTest(array17, 0, 7);
	}

	@Test
	public void testeSort93() {
		genericTest(array18, 4, 6);
	}

	@Test
	public void testeSort94() {
		genericTest(array9, 2, 7);
	}

	@Test
	public void testeSort95() {
		genericTest(array10, 4, 9);
	}

	@Test
	public void testeSort96() {
		genericTest(array11, 7, 8);
	}

	@Test
	public void testeSort97() {
		genericTest(array12, 2, 2);
	}

	@Test
	public void testeSort98() {
		genericTest(array13, 5, 6);
	}

	@Test
	public void testeSort99() {
		genericTest(array14, 7, 8);
	}

	@Test
	public void testeSort100() {
		genericTest(array15, 2, 3);
	}

	@Test
	public void testeSort101() {
		genericTest(array15, 0, 1);
	}

	@Test
	public void testeSort102() {
		genericTest(array15, 1, 1);
	}

}