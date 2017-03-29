import java.util.ArrayList;

import huffmanTree.HeapNode;
import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class BinaryHeapMinPriorityQueue <T extends HeapNode> implements MinPriorityQueue<T> {

	ArrayList<T> arrayList;

	@Override
	public T removeMin() throws NoElementInQueueException {
		if (arrayList.size() == 0) {
			throw new NoElementInQueueException("No element in queue");
		}
		T min = arrayList.get(0);
		int lastIndex = arrayList.size() - 1;
		T last = arrayList.get(lastIndex);
		arrayList.set(0, last);
		arrayList.remove(lastIndex);
		minHeapify(0);
		return min;
	}

	@Override
	public T getMin() throws NoElementInQueueException {
		if (arrayList.size() == 0) {
			throw new NoElementInQueueException("No element in queue");
		}
		return arrayList.get(0);
	}

	@Override
	public void insert(T nodeData) throws InvalidInputToQueueException {
		int index = arrayList.size();
		int newKey = nodeData.weight;
		nodeData.weight = Integer.MAX_VALUE;
		arrayList.add(index, nodeData);
		decreaseKey(index, newKey);
	}

	@Override
	public void decreaseKey(int index, int newKey) throws InvalidInputToQueueException {
		if ( index < 0 || index >= arrayList.size()) {
			throw new ArrayIndexOutOfBoundsException(
					String.format("Expected array index between 0 and %d but it was %d", arrayList.size() - 1, index));
		}
		T origValue = arrayList.get(index);
		int origKey = origValue.weight;
		// newValue > origValue
		if (  newKey > origKey) {
			throw new InvalidInputToQueueException("For decrease key operation new value should be less than orignal value");
		}
		else {
			origValue.weight = newKey;
			//arrayList.set(index, newValue);
			int parent = parent(index);
			T parentValue = arrayList.get(parent);
			//parentValue > newValue
			while( index > 0 &&  parentValue.weight > origValue.weight) {
				arrayList.set(index, parentValue);
				arrayList.set(parent, origValue);
				index = parent;
				parent = parent(index);
				parentValue = arrayList.get(parent);
			}
		}
	}

	@Override
	public void updateKey(int index, int newKey) throws InvalidInputToQueueException {
		if (index < 0 || index >= arrayList.size()) {
			throw new ArrayIndexOutOfBoundsException(
					String.format("Expected array index between 0 and %d but it was %d", arrayList.size() - 1, index));
		}
		T origValue = arrayList.get(index);
		int origKey = origValue.weight;
		// newValue > origValue
		if ( newKey > origKey ) {
			origValue.weight = newKey;
			//arrayList.set(index, origValue);
			minHeapify(index);
		} else {
			decreaseKey(index, newKey);
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int rightChild(int i) {
		return 2 * i + 2;
	}

	private int leftChild(int i) {
		return 2 * i + 1;
	}

	private boolean minHeapify(int index) {
		if (index < 0) {
			return false;
		}

		int left = leftChild(index);
		int right = rightChild(index);
		int smallest = index;
		//arrayList.get(left) < arrayList.get(smallest)
		if (left < arrayList.size() && arrayList.get(left).compareTo(arrayList.get(smallest)) < 0) {
			smallest = left;
		}
		//arrayList.get(right) < arrayList.get(smallest)
		if (right < arrayList.size() && arrayList.get(right).compareTo(arrayList.get(smallest)) < 0) {
			smallest = right;
		}

		if (smallest != index) {
			T temp = arrayList.get(index);
			arrayList.set(index, arrayList.get(smallest));
			arrayList.set(smallest, temp);
			return minHeapify(smallest);
		}
		return true;
	}

	private void buildMinHeap() {
		for (int i = (arrayList.size() / 2 - 1); i >= 0; i--) {
			minHeapify(i);
		}
	}

	public BinaryHeapMinPriorityQueue(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
		buildMinHeap();
	}
	
	public BinaryHeapMinPriorityQueue(T [] array) {
		this.arrayList = new ArrayList<>();
		for( T element: array) {
			arrayList.add(element);
		}
	}
	
	public BinaryHeapMinPriorityQueue() {
		this.arrayList = new ArrayList<>();
	}

	@Override
	public int size() {
		return arrayList.size();
	}

}
