import java.util.ArrayList;
public class Heap {
	private int[] Heap;
	private int size;
	private ArrayList<HuffTree> trees;

	private static final int FRONT = 1;

	//Constructor for MinHeap
	public Heap() {
		this.size = 0;
		trees = new ArrayList<HuffTree>();
	}

	//Returns the size of the heap
	public int getSize() {
		return size;
	}

	//Removes an element from the tree
	public HuffTree removeMin() {
		//edge case protection
		if (size == 0) return null;

		//grab the minimum element at the root and place it in min
		HuffTree min = trees.get(0);

		//set the root equal to the last element in the list
		trees.set(0, trees.get(size-1));

		//remove the last element in the list as it is now at the root
		trees.remove(size-1);
		size--;

		//current node, its left and right children, a temporary variable holding the minimum between left and right, and a temp HuffTree holder
		int curr = 0, left = 1, right = 2, toSwap = -1;
		HuffTree temp = null;

		/* while the left node is a valid node and the frequency of the current node is greater then the frequency of the left node
	 	 or the right node is a valid node and the frequency of the current node is greater then the frequency of the right node*/
		while (true) {

			if (right >= size) {

				if (left >= size) break;
				else toSwap = left;
			}
			else {

				if (trees.get(left).weight() <= trees.get(right).weight()) toSwap = left;
				else toSwap = right;
			}
			if (trees.get(curr).weight() > trees.get(toSwap).weight()) {

				temp = trees.get(curr);
				trees.set(curr, trees.get(toSwap));
				trees.set(toSwap, temp);

				curr = toSwap;
				left = 2*curr+1;
				right = 2*curr+2;
			}
			else break;
		}
		return min;
	}

	//Inserts an item to the tree
	public void insert(HuffTree toAdd) {
		trees.add(toAdd);
		size++;

		//the child was the node that we inserted
		int child = size-1, parent = (child-1)/2;
		HuffTree temp = null;

		/* while the parent isn't the child (means we must be at the root) and the frequency of the parent is 
	 	greater than that of the child  */
		while (parent != child && trees.get(parent).weight() > trees.get(child).weight()) {

			//swap parent and child
			temp = trees.get(parent);
			trees.set(parent, trees.get(child));
			trees.set(child, temp);

			child = parent;
			parent = (child-1)/2;
		}
	}
	
}
