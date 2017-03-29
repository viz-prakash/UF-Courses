import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class encoder {

	public static void main(String[] args) throws IOException, InvalidInputToQueueException, NoElementInQueueException {
		HashMap<Integer, Integer> frequencyTable = new HashMap<>();
		LinkedList<Integer> data = new LinkedList<>();
		
		if ( args.length != 1) {
			System.out.println("Please provide a file to encode.");
		}
		BufferedReader bf = new BufferedReader(new FileReader(new File(args[0])));
		String line = null;
		HuffmanTree huffmanTree = new HuffmanTree();
		
		// int [] frequencyTable = new int [1000000];
		while (true) {
			line = bf.readLine();
			if ( line == null || line.equals("")) {
				break;
			}
			int key = Integer.parseInt(line);
			Object value = frequencyTable.get(key);
			if (value != null) {
				frequencyTable.put(key, (Integer)value + 1);
			} else {
				frequencyTable.put(key, 1);
			}
			data.add(key);
			// frequencyTable[key]++;
		}
		bf.close();

		System.out.println("Number of unique frequency : " + frequencyTable.size());
		
		int iterateCount = 100;
		System.out.println("Number of times operations are going to be performed : " + iterateCount);
		
		long startTime = System.currentTimeMillis();
		for ( int i = 0 ; i < iterateCount ; i++ ) { 
			huffmanTree.binaryHeapHuffmanTree(frequencyTable);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Huffman Tree creation time using Binary Heap in ms per iteration : " + totalTime/iterateCount);
		
		startTime = System.currentTimeMillis();
		for ( int i = 0 ; i < iterateCount ; i++ ) { 
			huffmanTree.fourWayHeapHuffmanTree(frequencyTable);
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Huffman Tree creation time using 4-ary Heap in ms per iteration : " + totalTime/iterateCount);
		
		startTime = System.currentTimeMillis();
		for ( int i = 0 ; i < iterateCount ; i++ ) { 
			huffmanTree.pairingHeapHuffmanTree(frequencyTable);
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Huffman Tree creation time using Pairing Heap in ms per iteration : " + totalTime/iterateCount);
	}

}
