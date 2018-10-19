package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (element != null) {
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
}
