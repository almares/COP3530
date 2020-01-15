/** A Huffman coding tree */
public class HuffTree implements Comparable {
	private HuffBaseNode root;  

	/** Constructors */
	public HuffTree(char el, int wt) { 
		root = new HuffLeafNode(el, wt); 
	}

	public HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) { 
		root = new HuffInternalNode(l, r, wt); 
	}

	public HuffTree(HuffBaseNode r) {
		root = r;
	}

	public HuffBaseNode root() {
		return root; 
	}

	public int weight() { 
		return root.weight(); 
	}

	public int compareTo(Object t) {
		HuffTree that = (HuffTree)t;
		if (root.weight() < that.weight()) return -1;
		else if (root.weight() == that.weight()) return 0;
		else return 1;
	}

	
	
	
	public void traverseTree(HuffBaseNode node, String [] arr) {

		if (arr == null || node == null || node.isLeaf()) return;

		traverseTree(  ( (HuffInternalNode)(node) ).right(), arr, "1");

		traverseTree(  ( (HuffInternalNode)(node) ).left(), arr, "0");

	}

	public void traverseTree(HuffBaseNode node, String [] arr, String code) {

		if (node.isLeaf()) {
			 arr[((HuffLeafNode)(node)).value()] = (((HuffLeafNode)node)).value() + " " + code + "\n";
		}

		else {

			traverseTree(  ( (HuffInternalNode)(node) ).right(), arr, code + "1");

			traverseTree(  ( (HuffInternalNode)(node) ).left(), arr, code + "0");
		}
	}
}