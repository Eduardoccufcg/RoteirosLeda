package adt.hashtable.open;

import java.util.HashMap;
import java.util.Map;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Main {

	public static void main(String[] args) {
		
		Integer[] a = {2,7};
		
		System.out.println(duplicated(a));

		

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
	private static int duplicated(Integer[] array) {
		HashMap<Integer,Contador> mapa = new HashMap<Integer,Contador>();
		for(int i = 0; i < array.length;i++) {
			if(mapa.get(array[i]) == null) {
				mapa.put(array[i], new Contador());
			}else {
				((Contador) mapa.get(array[i])).setCount();
			
				
			}
		}
		for(Integer a : mapa.keySet()) {
			if(mapa.get(a).getCount() >= array.length / 2.0) {
				return a;
			}
			
		}
		throw new RuntimeException("Não ha esse elemento");
		
		
	}
	
	

}
