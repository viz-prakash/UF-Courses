import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import huffmanTree.HuffmanNode;

import static org.junit.Assert.assertEquals;

import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class ParingHeapMinPriorityQueueTest {

	public ArrayList<HuffmanNode> produceNodeDataList(ArrayList<Integer> list) {
		ArrayList<HuffmanNode> nodeDataList = new ArrayList<>();
		for (Object e : list) {
			if (e == null) {
				nodeDataList.add(null);
			} else {
				nodeDataList.add(new HuffmanNode((Integer) e));
			}
		}
		return nodeDataList;
	}
	
	@Test
	public void testInsertion() throws InvalidInputToQueueException {
		PairingHeapMinPriorityQueue<HuffmanNode> paringHeap = new PairingHeapMinPriorityQueue<>(
				produceNodeDataList(new ArrayList<Integer>(Arrays.asList(2, 3, 56, 12, -23))));

		assertEquals(produceNodeDataList(new ArrayList<Integer>(Arrays.asList(-23, 2, 12, 56, 3))),
				paringHeap.dumpAllElementsLevelWise());
	}
	
	@Test
	public void testRemoveMin() throws InvalidInputToQueueException, NoElementInQueueException {
		PairingHeapMinPriorityQueue<HuffmanNode> paringHeap = new PairingHeapMinPriorityQueue<>(
				produceNodeDataList(new ArrayList<Integer>(Arrays.asList(2, 3, 56, 12, -23))));
		
		assertEquals(-23, paringHeap.removeMin().weight);
		assertEquals(2, paringHeap.removeMin().weight);
		assertEquals(3, paringHeap.removeMin().weight);
		assertEquals(12, paringHeap.removeMin().weight);
		assertEquals(56, paringHeap.removeMin().weight);
	}
}
