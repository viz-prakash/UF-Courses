import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import huffmanTree.HuffmanNode;

import static org.junit.Assert.*;

import queueException.MinPriorityQueueException.InvalidInputToQueueException;

public class FourCacheOptimizedHeapTest {

	@Test
	public void buildEmptyHeapTest() {
		ArrayList<HuffmanNode> arrayList = new ArrayList<>(Arrays.asList());
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>(arrayList);
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(null, null, null)), fourCacheOptimizedHeap.array);
		arrayList = new ArrayList<>(Arrays.asList(new HuffmanNode(50)));
		fourCacheOptimizedHeap = new FourCacheOptimizedHeap<HuffmanNode>(arrayList);
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(null, null, null, new HuffmanNode(50))),
				fourCacheOptimizedHeap.array);

	}

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
	public void buildHeapTest() {
		ArrayList<HuffmanNode> arrayList = produceNodeDataList(
				new ArrayList<>(Arrays.asList(120, 200, 40, 250, 80, 100, 10, 42, 53, 84, 65, 93, 107)));
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>(arrayList);
		assertEquals(
				produceNodeDataList(new ArrayList<>(
						Arrays.asList(null, null, null, 10, 42, 40, 250, 80, 100, 200, 120, 53, 84, 65, 93, 107))),
				fourCacheOptimizedHeap.array);
	}

	@Test
	public void buildHeapTest1() {
		ArrayList<HuffmanNode> arrayList = produceNodeDataList(
				new ArrayList<>(Arrays.asList(120, 200, 40, 250, 80, 100, 10, 42, 53, 84, 65)));
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>(arrayList);
		assertEquals(
				produceNodeDataList(new ArrayList<>(
						Arrays.asList(null, null, null, 10, 42, 40, 250, 80, 100, 200, 120, 53, 84, 65))),
				fourCacheOptimizedHeap.array);
	}

	@Test
	public void buildHeapTest2() {
		ArrayList<HuffmanNode> arrayList = produceNodeDataList(new ArrayList<>(Arrays.asList(120, 200, 40, 250, 80, 100,
				10, 42, 53, 84, 65, 93, 107, 168, -10, 110, 178, 85, 5, 78, 18)));
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>(arrayList);
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, -10, 10, 40, 110, 5, 100, 200,
				42, 53, 84, 65, 93, 107, 168, 250, 120, 178, 85, 80, 78, 18))), fourCacheOptimizedHeap.array);
	}

	@Test
	public void buildHeapTest3() {
		ArrayList<HuffmanNode> arrayList = produceNodeDataList(new ArrayList<>(Arrays.asList(120, 200, 40, 250, 80, 100,
				10, 42, 53, 84, 65, 93, 107, 168, -10, 110, 178, 85, 5, 78, 18, 98)));
		FourCacheOptimizedHeap<HuffmanNode> fourCacheOptimizedHeap = new FourCacheOptimizedHeap<>(arrayList);
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, -10, 10, 40, 110, 5, 98, 200,
				42, 53, 84, 65, 93, 107, 168, 250, 120, 178, 85, 80, 78, 18, 100))), fourCacheOptimizedHeap.array);
	}

	@Test
	public void testDecreaseKey() throws InvalidInputToQueueException {
		FourCacheOptimizedHeap<HuffmanNode> fourHeapMinPriorityQueue = new FourCacheOptimizedHeap<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(4))));
		fourHeapMinPriorityQueue.decreaseKey(0, 2);
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, 2))),
				fourHeapMinPriorityQueue.array);
	}

	@Test
	public void testDecreaseKey1() throws InvalidInputToQueueException {
		ArrayList<HuffmanNode> arrayList = produceNodeDataList(new ArrayList<>(Arrays.asList(120, 200, 40, 250, 80, 100, 10, 42, 53, 84, 65,
				93, 107, 168, -10, 110, 178, 85, 5, 78, 18, 98)));
		FourCacheOptimizedHeap<HuffmanNode> fourHeapMinPriorityQueue = new FourCacheOptimizedHeap<>(
				new ArrayList<HuffmanNode>(arrayList));
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, -10, 10, 40, 110, 5, 98, 200, 42, 53, 84,
				65, 93, 107, 168, 250, 120, 178, 85, 80, 78, 18, 100))), fourHeapMinPriorityQueue.array);
		fourHeapMinPriorityQueue.decreaseKey(9, 15);
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, -10, 10, 15, 110, 5, 98, 200, 42, 53, 40,
				65, 93, 107, 168, 250, 120, 178, 85, 80, 78, 18, 100))), fourHeapMinPriorityQueue.array);
		fourHeapMinPriorityQueue.decreaseKey(2, -15);
		assertEquals(produceNodeDataList(new ArrayList<>(Arrays.asList(null, null, null, -15, 10, -10, 110, 5, 98, 200, 42, 53, 40,
				65, 93, 107, 168, 250, 120, 178, 85, 80, 78, 18, 100))), fourHeapMinPriorityQueue.array);
	}
}
