import java.util.Scanner;

public class Problem3 {
	// 3) Given 2 very large binary trees T1 and T2. Create an algorithm to
	// determine if T2 is a subtree of T1. Assume T1 is always much larger than T2.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		print("Example Input: \"1 2 3 4 5 6 7 8 9\"\n");
		print("In-order Traversal T1: ");
		String Tree1 = sc.next();
		// press Enter to get default input
		if (Tree1.length() == 1) {
			Tree1 = "1 2 3 4 5 6 7 8 9\n";
		}
		int[] t1n = getNumbers(Tree1);
		print("In-order Traversal T2: ");
		String Tree2 = sc.next();
		// default input
		if (Tree2.length() == 1) {
			Tree2 = "4 8 9\n";
		}
		int[] t2n = getNumbers(Tree2);
		Node t1 = createBT(t1n);
		Node t2 = createBT(t2n);
		boolean ist = IST2(t1, t2);
		print("Output: " + ist);

		sc.close();
	}
	
	public static boolean IST2(Node t1, Node t2) {
		boolean check = true;
		if (t2.getData() == t1.getData()) {
			if (t2.hasLeft()) {
				if (!t1.hasLeft()) {
					return false;
				} else {
					check = IST2(t1.getLeft(), t2.getLeft());
				}
			} else {
				if (t1.hasLeft()) {
					return false;
				}
			}
			if (t2.hasRight()) {
				if (!t1.hasRight()) {
					return false;
				} else {
					check = IST2(t1.getRight(), t2.getRight());
				}
			} else {
				if (t1.hasRight()) {
					return false;
				}
			}
		} else {
			check = IST2(t1.getLeft(), t2);
			if (check) {
				return check;
			}
			check = IST2(t1.getRight(), t2);
		}
		return check;
	}
	
	public static boolean check(Node t1, Node t2) {
		return false;
	}

	public static int getHeight(Node root) {
		Node test = root;
		int height = 1;
		while (test.hasLeft()) {
			test = test.getLeft();
			height++;
		}
		return height;
	}

	public static Node createBT(int[] nums) {
		return createBT(nums, 0);
	}

	public static Node createBT(int[] nums, int i) {
		Node root = new Node(nums[i]);
		// i + 1 because i initially equals 0
		if (i < nums.length - i - 1) {
			root.addLeftNode(createBT(nums, (i + 1) * 2 - 1));
			if (i < nums.length - i - 2) {
				root.addRightNode(createBT(nums, (i + 1) * 2));
			}
		}
		return root;
	}

	public static int[] getNumbers(String Tree) {
		int size = getInputSize(Tree);
		int[] numbers = new int[size];
		for (int i = 0; i < size - 1; i++) {
			numbers[i] = Integer.valueOf(Tree.substring(0, Tree.indexOf(' ')));
			Tree = Tree.substring(Tree.indexOf(' ') + 1);
		}
		Tree = Tree.substring(Tree.indexOf(' ') + 1);
		numbers[size - 1] = Integer.valueOf(Tree.substring(Tree.indexOf(' ') + 1, Tree.length() - 1));
		return numbers;
	}

	public static int getInputSize(String s) {
		int size = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				i++;
				size++;
			}
		}
		return size;
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void newline() {
		System.out.println();
	}

	// Testing purposes. Example s = "010111010." 0 = left child, 1 = right child.
	public static Node getChild(Node root, String s) {

		Node child = root;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				child = child.getLeft();
			} else {
				child = child.getRight();
			}
		}
		return child;
	}
}
