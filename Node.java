/*
 * Griffin Ryan
 * HashingCompression project, Node.java
 * CSS 342, Spring 2022
 */

/**
 * Uses a Node class in the CodingTree to
 * compress a .txt file.
 *
 * @author Griffin Ryan (glryan@uw.edu)
 * @version Spring 2022
 */
public class Node implements Comparable<Node> {

	private final Node left;
	private final Node right;
	private final String data;
	private final int weight;

	/**
	 * The Node() constructor instantiates a new node for
	 * Huffman encoding. Includes weight of String.
	 *
	 * @param data   String in the node.
	 * @param left   pointer to node object to left of this node.
	 * @param right  pointer to node object to right of this node.
	 * @param weight int value for weight of this node's String.
	 */
	public Node(String data, Node left, Node right, int weight) {
		this.left = left;
		this.right = right;
		this.data = data;
		this.weight = weight;
	}

	/**
	 * getLeft returns the Node() object that is
	 * left of this node.
	 *
	 * @return Left node of the current node.
	 */
	public Node getLeft() {
		return this.left;
	}

	/**
	 * getRight returns the Node() object that is
	 * right of this node.
	 *
	 * @return Right node of the current node.
	 */
	public Node getRight() {
		return this.right;
	}

	/**
	 * getData returns the String data
	 * of this current Node() object.
	 *
	 * @return String of this node.
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * getWeight returns the weight of the
	 * String of this current Node() object.
	 *
	 * @return Weight of this node's String.
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * isLeaf is a boolean method that returns
	 * true if the current Node() object is a leaf.
	 *
	 * @return Boolean to determine if node is a leaf.
	 */
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}

	/**
	 * toString is a method to return the current
	 * node data fields to a String. Ex: "Char: A, Weight: 10"
	 *
	 * @return this current node to a String.
	 */
	public String toString() {
		String letter = "Char: ";
		String weight = ", Weight: ";
		return letter + this.data + weight + this.weight;
	}

	/**
	 * compareTo compares the weight
	 * of two nodes.
	 *
	 * @param temp is the node to compare to.
	 * @return compare is an int to check nodes.
	 */
	@Override
	public int compareTo(Node temp) {
		int compare = 0;
		if (this.weight > temp.getWeight()) {
			compare = 1;
		} else if (this.weight < temp.getWeight()) {
			compare = -1;
		}
		return compare;
	}
}

