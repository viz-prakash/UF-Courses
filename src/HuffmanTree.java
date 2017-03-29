import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import huffmanTree.HuffmanNode;
import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class HuffmanTree {

	/*
	 * 1) read the input numbers and generate a frequency using an array or
	 * arraylist 2) build a Huffman tree with different ways. Those are : Binary
	 * heap, 4-way cached-optimize heap and Paring heap 3) measure the run time
	 * for creation of Huffman tree using different methodologies 4) Create a
	 * Huffman code for the given input with best methodology and output
	 * codetable.txt 5) Encode the input, by reading again from file, with
	 * created code table and write output in encoded.bin 6) read the
	 * encoded.bin and codetable.txt and generate back original text.
	 */

	private HuffmanNode root;
	//private HashMap<Integer, Integer> frequencyTable;
	//private LinkedList<Integer> data;

	public HuffmanTree() {
		root = null;
		//frequencyTable = new HashMap<>();
		//data = new LinkedList<>();
	}
	
	public HuffmanNode binaryHeapHuffmanTree(HashMap<Integer, Integer> frequencyTable) throws InvalidInputToQueueException, NoElementInQueueException {
		Iterator<Entry<Integer, Integer>> iterator = frequencyTable.entrySet().iterator();

		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();
			HuffmanNode nodeData = new HuffmanNode(entry.getKey(), entry.getValue());
			binaryHeapMinPriorityQueue.insert(nodeData);
		}

		while (binaryHeapMinPriorityQueue.size() > 1) {
			HuffmanNode hfNode0 = binaryHeapMinPriorityQueue.removeMin();
			HuffmanNode hfNode1 = binaryHeapMinPriorityQueue.getMin();
			HuffmanNode hfNode = new HuffmanNode(hfNode1);
			hfNode1.left = hfNode0;
			hfNode1.right = hfNode;
			binaryHeapMinPriorityQueue.updateKey(0, hfNode0.weight + hfNode1.weight);
		}

		this.root = binaryHeapMinPriorityQueue.getMin();
		return root;
	}

	public HuffmanNode fourWayHeapHuffmanTree(HashMap<Integer, Integer> frequencyTable) throws NoElementInQueueException, InvalidInputToQueueException {
		Iterator<Entry<Integer, Integer>> iterator = frequencyTable.entrySet().iterator();

		
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();
			fourCacheOptimizedHeap.insert(new HuffmanNode(entry.getKey(), entry.getValue()));
		}

		while (fourCacheOptimizedHeap.size() > 1) {
			HuffmanNode hfNode0 = fourCacheOptimizedHeap.removeMin();
			HuffmanNode hfNode1 = fourCacheOptimizedHeap.getMin();
			HuffmanNode hfNode = new HuffmanNode(hfNode1);
			hfNode1.left = hfNode0;
			hfNode1.right = hfNode;
			fourCacheOptimizedHeap.updateKey(0, hfNode0.weight + hfNode1.weight);
		}

		this.root = fourCacheOptimizedHeap.getMin();
		//walkHuffmanTree(huffmanTree.root);
		return root;
	}

	public HuffmanTree pairingHeapHuffmanTree(HashMap<Integer, Integer> frequencyTable) throws NoElementInQueueException, InvalidInputToQueueException {
		Iterator<Entry<Integer, Integer>> iterator = frequencyTable.entrySet().iterator();

		PairingHeapMinPriorityQueue<HuffmanNode> pairingHeapMinPriorityQueue = new PairingHeapMinPriorityQueue<>();
		while(iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();
			pairingHeapMinPriorityQueue.insert(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
		
		while(pairingHeapMinPriorityQueue.size() > 1) {
			HuffmanNode hfNode0 = pairingHeapMinPriorityQueue.removeMin();
			HuffmanNode hfNode1 = pairingHeapMinPriorityQueue.removeMin();
			HuffmanNode hfNode = new HuffmanNode(hfNode0.weight + hfNode1.weight);
			pairingHeapMinPriorityQueue.insert(hfNode);
		}
		
		this.root = pairingHeapMinPriorityQueue.getMin();
		//walkHuffmanTree(huffmanTree.root);

		return null;
	}

	/**
	 * In-order traversal of tree
	 * 
	 * @param root
	 */
	public static void walkHuffmanTree(HuffmanNode root) {
		if (root == null)
			return;
		walkHuffmanTree(root.left);
		System.out.print("(" + root.data + "," + root.weight + "), " );
		walkHuffmanTree(root.right);
	}

	public static void main(String[] args) throws IOException, InvalidInputToQueueException, NoElementInQueueException {
		// Scanner scanner = new Scanner(new File(args[0]));
				
		//walkHuffmanTree(huffmanTree.root);
		
	}

	public HuffmanNode getRoot() {
		return root;
	}

	public void setRoot(HuffmanNode root) {
		this.root = root;
	}

	/*public HashMap<Integer, Integer> getFrequencyTable() {
		return frequencyTable;
	}

	public void setFrequencyTable(HashMap<Integer, Integer> frequencyTable) {
		this.frequencyTable = frequencyTable;
	}

	*/
	/*public LinkedList<Integer> getData() {
		return data;
	}

	public void setData(LinkedList<Integer> data) {
		this.data = data;
	}
*/
	
}
