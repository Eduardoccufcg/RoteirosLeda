import adt.heap.HeapImpl;

public class Main {

	public static void main(String[] args) {
	    HeapImpl<Integer> heap = new HeapImpl<Integer>((o1,o2) -> o2-o1);
	    heap.insert(15);
	    heap.insert(22);
	    heap.insert(25);
	    heap.insert(52);
	    heap.insert(11);
	    heap.insert(77);
	    System.out.print(heap.size());
	    System.out.println(heap.extractRootElement());
	    System.out.println(heap.extractRootElement());
	    System.out.println(heap.extractRootElement());

	    

	}

}
