import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import huffmanTree.HeapNode;
import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class PairingHeapMinPriorityQueue<T extends HeapNode> implements MinPriorityQueue<T> {

	private int size;
	private class Node {
		private Node left;  // link to left sibling. First child points to parent.
		private Node right; // link to right sibling. Last sibling has null.
		private T data;  // data
		private Node child; // link to first node of children list.
	}
	
	private Node root;
	
	/**
	 * remove the minimum element in the heap and returns its value
	 */
	@Override
	public T removeMin() throws NoElementInQueueException {
		// TODO Auto-generated method stub 
		// need to do it in two ways first 2-pass merge and n-pass merge
		if ( root == null) {
			throw new NoElementInQueueException("No elements in queue");
		}
		T data = root.data;
		root = removeMinFromSubtree(root);
		size--;
		return data;
	}
	
	private Node removeMinFromSubtree(Node root) {
		Node firstSibling = root.child;
		root.child = null;
		return twoPassMerge(firstSibling);
		//return multiPassMerge(firstSibling);
	}
	
	private Node twoPassMerge(Node node) {
		// First round merge
		LinkedList<Node> fifoQueue = new LinkedList<>();
		while(node != null) {
			node.left = null;
			Node first = node;
			Node second = node.right;
			first.right = null;
			if ( second == null) {
				second = fifoQueue.pollLast();
				if ( second == null) {
					return first;
					/*fifoQueue.add(first);
					break;*/
				}
				node = null;
			} else {
				node = second.right;
			}
			second.left = second.right = null;
			fifoQueue.add(meld(first,second));
		}
		
		//second round merge
		Node subtreeRoot = fifoQueue.pollLast();
		while( fifoQueue.size() != 0 ) {
			subtreeRoot = meld(subtreeRoot, fifoQueue.pollLast());
		}
		return subtreeRoot;
	}
	
	private Node multiPassMerge(Node node) {
		LinkedList<Node> fifoQueue = new LinkedList<>();
		while(node != null) {
			node.left = null;
			Node first = node;
			Node second = node.right;
			first.right = null;
			if ( second == null) {
				second = fifoQueue.pollFirst();
				if ( second == null) {
					/*fifoQueue.add(first);
					break;*/
					return first;
				}
				node = null;
			} else {
				node = second.right;
			}
			second.left = second.right = null;
			fifoQueue.add(meld(first,second));
		}
		
		while( fifoQueue.size() > 1) {
			Node first = fifoQueue.pollFirst();
			Node second = fifoQueue.pollFirst();
			fifoQueue.add(meld(first,second));
		}
		return fifoQueue.pollFirst();
	}

	/**
	 * returns the value of min element in heap
	 */
	@Override
	public T getMin() throws NoElementInQueueException {
		return root.data;
	}

	/**
	 * inserts a new element in heap
	 */
	@Override
	public void insert(T element) throws InvalidInputToQueueException {
		Node node = new Node();
		node.data = element;
		node.left = null;
		node.right = null;
		node.child = null;
		
		root = meld(root, node);
		size++;
	}
	
	/**
	 * Meld the two given heap in one and return the resultant heap root node
	 * @param heap1
	 * @param heap2
	 * @return
	 */
	public Node meld(Node heap1, Node heap2) {
		if ( heap1 == null) {
			return heap2;
		}
		if ( heap2 == null) {
			return heap1;
		}
		//heap1.data <= heap2.data
		if ( heap1.data.compareTo(heap2.data) <= 0) {
			heap2.right = heap1.child;
			if ( heap1.child != null ) heap1.child.left = heap2;
			heap2.left = heap1;
			heap1.child = heap2;
			return heap1;
		}
		else {
			heap1.right = heap2.child;
			if ( heap2.child != null ) heap2.child.left = heap1;
			heap1.left = heap2;
			heap2.child = heap1;
			return heap2;
		}
	}

	@Override
	public void updateKey(int index, int newValue) throws InvalidInputToQueueException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decreaseKey(int index, int newValue) throws InvalidInputToQueueException {
		// TODO Auto-generated method stub
		throw new InvalidInputToQueueException("Operation not supported");
	}
	
	/**
	 * decrease the key of given node in heap
	 * @param node
	 * @param newValue
	 * @throws InvalidInputToQueueException
	 */
	public void decreaseKey( Node node, int newValue) throws InvalidInputToQueueException {
		if ( node == null) {
			throw new InvalidInputToQueueException("Not a valid node for decrease key operation");
		}
		if ( newValue > node.data.weight) {
			throw new InvalidInputToQueueException("New value is greater than original value");
		}
		
		node.data.weight = newValue;
		// Take out the node
		detach(node);
		
		this.root = meld(this.root, node);
	}
	
	/*
	 *  detach the node from doubly linked list of siblings
	 */
	private void detach(Node node) {
		if ( node.left.child == node) {
			node.left.child = node.right;
			node.right.left = node.left;
		}
		else if (node.right!= null) {
			node.left.right = node.right;
			node.right.left = node.left;
		} else {
			node.left.right = null;
		}
		node.left = null;
		node.right = null;
	}

	/**
	 * removes a arbitrary node from heap
	 * @param node
	 * @throws NoElementInQueueException
	 */
	public void remove(Node node) throws NoElementInQueueException {
		if ( node == root) {
			removeMin();
			return;
		}
		
		// Take the node out
		detach(node);
		Node newRootOfSubtree = removeMinFromSubtree(node);
		this.root = meld(root, newRootOfSubtree);
		size--;
	}
	
	public ArrayList<T> dumpAllElementsLevelWise() {
		Deque<Node> fifoQueue = new ArrayDeque<>();
		ArrayList<T> elements = new ArrayList<>();
		if ( root == null) {
			return elements;
		}
		fifoQueue.addLast(root);
		while(fifoQueue.size() != 0) {
			Node curRoot = fifoQueue.pollFirst();
			elements.add(curRoot.data);
			Node curChild = curRoot.child;
			while(curChild != null) {
				fifoQueue.add(curChild);
				curChild = curChild.right;
			}
		}
		return elements;
	}
	
	
	public PairingHeapMinPriorityQueue() {
		root = null;
	}
	
	public PairingHeapMinPriorityQueue(T [] array) throws InvalidInputToQueueException {
		root = null;
		this.size = 0;
		for (T element : array) {
			insert(element);
			this.size++;
		}
	}
	
	public PairingHeapMinPriorityQueue(ArrayList<T> array) throws InvalidInputToQueueException {
		root = null;
		this.size = 0;
		for ( T element : array) {
			insert(element);
			this.size++;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	
}
