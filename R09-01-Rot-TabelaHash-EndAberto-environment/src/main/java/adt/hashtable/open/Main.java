package adt.hashtable.open;

import java.util.HashMap;
import java.util.Map;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Main {

	public static void main(String[] args) {
		
		Integer[] a = {1,4,2,3,7,9};
		int soma = 79;
		System.out.println(findPlus(a,soma));

		

	}
	/**
	 * 
	 * @param c1 Constante 1
	 * @param c2 Constante 2
	 * @param m Tamanho do array
	 */
	private static boolean verificaCompleteHash(int c1, int c2,int m) {
		int[] aux = new int[m];
		int a = 1;
		for(int i = 0; i < m; i++) {
			int index = (c1*i + c2*i*i) % m;
			if(aux[index] == a) {
				return false;
			}
			aux[index] = a;
			
			
		}
		return true;
		

	}
	private static String findPlus(Integer[] array, int soma) {
		Map mapa = new HashMap<Integer,Integer>();
		for(int i = 0; i < array.length;i++) {
			mapa.put(soma- array[i],i);
		}
		for(int i = 0; i < array.length;i++) {
			if(mapa.get(array[i]) != null) {
				
				return "(" + array[i] + "," + array[(int) mapa.get(array[i])] + ")";
			}
		}
		return "Nao existe";
	}
	

}
