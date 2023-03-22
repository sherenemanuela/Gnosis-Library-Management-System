package iterator;

import java.util.ArrayList;

public class FIFOiterator<T> {
	private ArrayList<T> list;
	private int currentIndex;
	
	public FIFOiterator(ArrayList<T> list) {
		this.list = list;
		currentIndex = 0;
	}

	public T getNext() {
		if(hasNext()) {
			currentIndex++;
			return list.get(currentIndex - 1);
		}
		System.out.println("List is empty!");
		return null;
	}

	public boolean hasNext() {
		return currentIndex < list.size();
	}

	public boolean iteratable() {
		this.currentIndex = 0;
		if(hasNext()) 
			return true;
		return false;
	}
}
