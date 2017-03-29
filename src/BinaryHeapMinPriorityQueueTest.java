import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import huffmanTree.HuffmanNode;
import queueException.MinPriorityQueueException.InvalidInputToQueueException;
import queueException.MinPriorityQueueException.NoElementInQueueException;

public class BinaryHeapMinPriorityQueueTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testBinaryHeap() throws NoElementInQueueException {
		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(2), new HuffmanNode(3), new HuffmanNode(56),
						new HuffmanNode(12), new HuffmanNode(-23))));
		assertEquals(new ArrayList<HuffmanNode>(
				Arrays.asList(new HuffmanNode(-23), new HuffmanNode(2), new HuffmanNode(56), new HuffmanNode(12), new HuffmanNode(3))),
				binaryHeapMinPriorityQueue.arrayList);
		// assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 2, 56, 12,
		// 3)), binaryHeapMinPriorityQueue.arrayList);
		assertEquals(new HuffmanNode(-23), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(2), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(3), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(12), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(56), binaryHeapMinPriorityQueue.removeMin());
		// thrown.expect(NoElementInQueueException.class);
		// binaryHeapMinPriorityQueue.removeMin();
	}

	@Test
	public void testBinaryHeap1() throws NoElementInQueueException {

		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(4), new HuffmanNode(3), new HuffmanNode(56),
						new HuffmanNode(12), new HuffmanNode(-23), new HuffmanNode(0))));
		/*
		 * BinaryHeapMinPriorityQueue binaryHeapMinPriorityQueue = new
		 * BinaryHeapMinPriorityQueue( new ArrayList<Integer>(Arrays.asList(4,
		 * 3, 56, 12, -23, 0)));
		 */
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-23), new HuffmanNode(3), new HuffmanNode(0),
				new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(56))), binaryHeapMinPriorityQueue.arrayList);
		// assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 3, 0, 12, 4,
		// 56)), binaryHeapMinPriorityQueue.arrayList);
		assertEquals(new HuffmanNode(-23), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(0), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(3), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(4), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(12), binaryHeapMinPriorityQueue.removeMin());
		assertEquals(new HuffmanNode(56), binaryHeapMinPriorityQueue.getMin());
		assertEquals(new HuffmanNode(56), binaryHeapMinPriorityQueue.removeMin());
		thrown.expect(NoElementInQueueException.class);
		binaryHeapMinPriorityQueue.getMin();
	}

	@Test
	public void testUpdateKey() throws InvalidInputToQueueException {
		/*
		 * BinaryHeapMinPriorityQueue binaryHeapMinPriorityQueue = new
		 * BinaryHeapMinPriorityQueue( new ArrayList<Integer>(Arrays.asList(4,
		 * 3, 56, 12, -23, 0)));
		 */
		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(4), new HuffmanNode(3), new HuffmanNode(56),
						new HuffmanNode(12), new HuffmanNode(-23), new HuffmanNode(0))));
		binaryHeapMinPriorityQueue.updateKey(5, -3);
		// assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 3, -3, 12, 4,
		// 0)), binaryHeapMinPriorityQueue.arrayList);
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-23), new HuffmanNode(3), new HuffmanNode(-3),
				new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(0))), binaryHeapMinPriorityQueue.arrayList);

		binaryHeapMinPriorityQueue.insert(new HuffmanNode(-4));
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(12));
		binaryHeapMinPriorityQueue.updateKey(2, 10);
		/*
		 * assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 3, -3, 12, 4,
		 * 0, 10, 12)), binaryHeapMinPriorityQueue.arrayList);
		 */
		assertEquals(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-23), new HuffmanNode(3), new HuffmanNode(-3),
						new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(0), new HuffmanNode(10), new HuffmanNode(12))),
				binaryHeapMinPriorityQueue.arrayList);

		binaryHeapMinPriorityQueue.updateKey(0, 10);
		/*
		 * assertEquals(new ArrayList<Integer>(Arrays.asList(-3, 3, 0, 12, 4,
		 * 10, 10, 12)), binaryHeapMinPriorityQueue.arrayList);
		 */
		assertEquals(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-3), new HuffmanNode(3), new HuffmanNode(0),
						new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(10), new HuffmanNode(10), new HuffmanNode(12))),
				binaryHeapMinPriorityQueue.arrayList);

		binaryHeapMinPriorityQueue.updateKey(0, 15);
		/*
		 * assertEquals(new ArrayList<Integer>(Arrays.asList(0, 3, 10, 12, 4,
		 * 15, 10, 12)), binaryHeapMinPriorityQueue.arrayList);
		 */
		assertEquals(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(0), new HuffmanNode(3), new HuffmanNode(10),
						new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(15), new HuffmanNode(10), new HuffmanNode(12))),
				binaryHeapMinPriorityQueue.arrayList);
		binaryHeapMinPriorityQueue.updateKey(0, 7);
		/*
		 * assertEquals(new ArrayList<Integer>(Arrays.asList(3, 4, 10, 12, 7,
		 * 15, 10, 12)), binaryHeapMinPriorityQueue.arrayList);
		 */
		assertEquals(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(3), new HuffmanNode(4), new HuffmanNode(10),
						new HuffmanNode(12), new HuffmanNode(7), new HuffmanNode(15), new HuffmanNode(10), new HuffmanNode(12))),
				binaryHeapMinPriorityQueue.arrayList);
	}

	@Test
	public void testInsertKey() throws InvalidInputToQueueException {
		/*
		 * BinaryHeapMinPriorityQueue binaryHeapMinPriorityQueue = new
		 * BinaryHeapMinPriorityQueue( new ArrayList<Integer>(Arrays.asList(4,
		 * 3, 56, 12, -23, 0)));
		 */

		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(4), new HuffmanNode(3), new HuffmanNode(56),
						new HuffmanNode(12), new HuffmanNode(-23), new HuffmanNode(0))));
		binaryHeapMinPriorityQueue.updateKey(5, -3);

		// assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 3, -3, 12, 4,
		// 0)), binaryHeapMinPriorityQueue.arrayList);

		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-23), new HuffmanNode(3), new HuffmanNode(-3),
				new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(0))), binaryHeapMinPriorityQueue.arrayList);

		binaryHeapMinPriorityQueue.insert(new HuffmanNode(-4));
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(12));
		/*
		 * assertEquals(new ArrayList<Integer>(Arrays.asList(-23, 3, -4, 12, 4,
		 * 0, -3, 12)), binaryHeapMinPriorityQueue.arrayList);
		 */

		assertEquals(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(-23), new HuffmanNode(3), new HuffmanNode(-4),
						new HuffmanNode(12), new HuffmanNode(4), new HuffmanNode(0), new HuffmanNode(-3), new HuffmanNode(12))),
				binaryHeapMinPriorityQueue.arrayList);

		binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<HuffmanNode>(new ArrayList<HuffmanNode>());
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(50));
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(50))), binaryHeapMinPriorityQueue.arrayList);
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(100));
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(75));
		binaryHeapMinPriorityQueue.insert(new HuffmanNode(30));
		assertEquals(
				new ArrayList<HuffmanNode>(
						Arrays.asList(new HuffmanNode(30), new HuffmanNode(50), new HuffmanNode(75), new HuffmanNode(100))),
				binaryHeapMinPriorityQueue.arrayList);

	}

	@Test
	public void testDecreaseKey() throws InvalidInputToQueueException {
		BinaryHeapMinPriorityQueue<HuffmanNode> binaryHeapMinPriorityQueue = new BinaryHeapMinPriorityQueue<>(
				new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(4))));
		binaryHeapMinPriorityQueue.decreaseKey(0, 2);
		assertEquals(new ArrayList<HuffmanNode>(Arrays.asList(new HuffmanNode(2))), binaryHeapMinPriorityQueue.arrayList);
	}
}
