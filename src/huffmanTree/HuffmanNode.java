package huffmanTree;

public class HuffmanNode extends HeapNode {

	public int data;
	public HuffmanNode left;
	public HuffmanNode right;
	
	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) {
			return false;
		}
		if ( ! HuffmanNode.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final HuffmanNode nodeData = (HuffmanNode) obj;
		if ( this.data != nodeData.data || this.weight != nodeData.weight ) {
			return false;
		}
		else return true;
	}
	@Override
	public int compareTo(HeapNode o) {
		return this.weight - o.weight;
	}

	@Override
	public int compare(HeapNode o1, HeapNode o2) {
		return o1.weight - o2.weight;
	}
	
	public HuffmanNode(int data, int frequncy) {
		super(frequncy);
		this.data = data;
		this.left = this.right = null;
	}
	
	public HuffmanNode(int frequency) {
		super(frequency);
		this.left = this.right = null;
	}
	
	public HuffmanNode(HuffmanNode huffmanNode) {
		super( huffmanNode.weight);
		this.data = huffmanNode.data;
		this.left = huffmanNode.left;
		this.right = huffmanNode.right;
	}
}
