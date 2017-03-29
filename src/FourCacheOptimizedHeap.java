import java.util.ArrayList;

import huffmanTree.HeapNode;
import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class FourCacheOptimizedHeap<T extends HeapNode> implements MinPriorityQueue<T> {

	public final int D;
	ArrayList<T> array;

	@Override
	public T removeMin() throws NoElementInQueueException {
		if (array.size() <= 3) {
			throw new NoElementInQueueException("There are no elements in queue");
		}
		T min = array.get(3);
		int lastElementIndex = array.size() - 4;
		array.set(3, array.get(lastElementIndex));
		array.remove(lastElementIndex);
		minHeapify(0);
		return min;
	}

	@Override
	public T getMin() throws NoElementInQueueException {
		if (array.size() <= 3) {
			throw new NoElementInQueueException("There are no elements in queue");
		}
		int firstElementIndex = 3;
		return array.get(firstElementIndex);
	}

	@Override
	public void insert(T element) throws InvalidInputToQueueException {
		int newElementIndex = array.size() - 3;
		int key = element.weight;
		element.weight = Integer.MAX_VALUE;
		array.add(element);
		decreaseKey(newElementIndex, key);
	}

	@Override
	public void updateKey(int index, int newkey) throws InvalidInputToQueueException {
		if (index < 0 || index >= (array.size() - 3)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T origValue = array.get(index + 3);
		// array.get(index + 3)
		if (newkey > origValue.weight) {
			origValue.weight = newkey;
			// array.set(index + 3, origValue);
			minHeapify(index);
		} else {
			decreaseKey(index, newkey);
		}
	}

	@Override
	public void decreaseKey(int index, int newKey) throws InvalidInputToQueueException {
		if (index < 0 || index >= (array.size() - 3)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T origValue = array.get(index + 3);
		// array.get(index + 3)
		if (newKey > origValue.weight) {
			throw new InvalidInputToQueueException("New value is greater than original value");
		}
		// array.set(index + 3, newKey);
		origValue.weight = newKey;
		T curElement = array.get(index + 3);
		int parentIndex = parent(index);
		T parent = array.get(parentIndex + 3);
		while (index > 0 && curElement.weight < parent.weight) {
			array.set(index + 3, parent);
			array.set(parentIndex + 3, curElement);
			index = parentIndex;
			parentIndex = parent(index);
			curElement = array.get(index + 3);
			parent = array.get(parentIndex + 3);
		}
	}

	private int parent(int index) {
		return (index - 1) / D;
	}

	private int firstChild(int index) {
		return (index * D + 1);
	}

	private void buildHeap() {
		int lastElementIndex = array.size() - 3 - 1;
		if (lastElementIndex <= 0) {
			return;
		}

		int lastParentIndex = parent(lastElementIndex);
		for (int i = lastParentIndex; i >= 0; i--) {
			minHeapify(i);
		}
	}

	private void minHeapify(int index) {
		if (index < 0) {
			return;
		}

		int numOfElements = array.size() - 3;
		int firstChildIndex = firstChild(index);

		// find the minimum
		int smallestElementIndex = index;
		for (int i = 0; i < D && firstChildIndex + i < numOfElements; i++) {
			// array.get(smallestElementIndex + 3) > array.get(firstChildIndex +
			// i + 3)
			if (array.get(smallestElementIndex + 3).weight > array.get(firstChildIndex + i + 3).weight) {
				smallestElementIndex = firstChildIndex + i;
			}
		}
		if (smallestElementIndex != index) {
			T temp = array.get(index + 3);
			array.set(index + 3, array.get(smallestElementIndex + 3));
			array.set(smallestElementIndex + 3, temp);
			minHeapify(smallestElementIndex);
		}
	}

	public FourCacheOptimizedHeap(ArrayList<T> arrayList) {
		D = 4;
		this.array = new ArrayList<>();
		/*
		 * this.array.add( (Integer.MIN_VALUE));
		 * this.array.add(Integer.MIN_VALUE); this.array.add(Integer.MIN_VALUE);
		 */
		this.array.add(null);
		this.array.add(null);
		this.array.add(null);
		for (T element : arrayList) {
			this.array.add(element);
		}
		buildHeap();
	}

	public FourCacheOptimizedHeap(T[] array) {
		D = 4;
		this.array = new ArrayList<>();
		/*
		 * this.array.add(Integer.MIN_VALUE); this.array.add(Integer.MIN_VALUE);
		 * this.array.add(Integer.MIN_VALUE);
		 */
		this.array.add(null);
		this.array.add(null);
		this.array.add(null);
		for (T element : array) {
			this.array.add(element);
		}
		buildHeap();
	}
	
	public FourCacheOptimizedHeap() {
		D = 4;
		this.array = new ArrayList<>();
		this.array.add(null);
		this.array.add(null);
		this.array.add(null);
	}

	@Override
	public int size() {
		return array.size() - 3;
	}

}
