package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         if (element != null && this.search(element) == null) {
            int i = 0;
            while (i < this.table.length) {
               int j = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
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
         int index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
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
         int index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
         if (this.table[index] == null) {
            break;
         } else if (element.equals(this.table[index])) {
            elem = (T) this.table[index];
            break;
         }
         i++;
      }
      return elem;
   }

   @Override
   public int indexOf(T element) {
      int i = 0;
      int IndexOf = -1;
      while (i < this.table.length) {
         int index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
         if (this.table[index] == null) {
            break;
         } else if (element.equals(this.table[index])) {
            IndexOf = index;
            break;
         } else {
            i++;
         }
      }
      return IndexOf;
   }

}
