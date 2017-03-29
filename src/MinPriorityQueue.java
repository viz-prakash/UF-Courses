import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

import huffmanTree.HeapNode;
public interface MinPriorityQueue<T extends HeapNode>{
	
	public T removeMin() throws NoElementInQueueException;
	public T getMin() throws NoElementInQueueException;
	public void insert(T nodeData) throws InvalidInputToQueueException;
	public void updateKey(int index, int newKey) throws InvalidInputToQueueException;
	public void decreaseKey(int index, int newKey) throws InvalidInputToQueueException;
	public int size();
}
