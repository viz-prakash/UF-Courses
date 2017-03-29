package huffmanTree;

import java.util.Comparator;

public abstract class HeapNode implements Comparable<HeapNode>, Comparator<HeapNode>{
	public int weight;

	public HeapNode(int frequency) {
		this.weight = frequency;
	}
	
	
}
