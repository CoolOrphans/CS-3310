
public class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public int getData() {
		return data;
	}

	public void addLeftNode(Node root) {
		left = root;
	}

	public void addRightNode(Node root) {
		right = root;
	}

	public void addRight(int data) {
		right = new Node(data);
	}

	public void addLeft(int data) {
		left = new Node(data);
	}

	public boolean hasLeft() {
		if (left != null) {
			return true;
		}
		return false;
	}

	public boolean hasRight() {
		if (right != null) {
			return true;
		}
		return false;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}
}
