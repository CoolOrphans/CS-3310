import java.util.Scanner;

public class Dependencies {

	public static void main(String[] args) {
		print("Example Projects: a, b, c, d, e, f\n");

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		print("Projects: ");
		// EXAMPLE INPUT: a, b, c, d, e, f
		String Projs = sc.next();
		// use default by just pressing enter
		if (Projs.length() == 1) {
			Projs = "a, b, c, d, e, f";
		}
		char[] projs = new char[(Projs.length() + 2) / 3];
		for (int i = 0; i < projs.length; i++) {
			projs[i] = Projs.charAt(i * 3);
		}

		print("Example Dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)\n");
		print("Dependencies: ");
		// EXAMPLE INPUT: (a, d), (f, b), (b, d), (f, a), (d, c)
		String Deps = sc.next();
		// use default by just pressing enter
		if (Deps.length() == 1) {
			Deps = "(a, d), (f, b), (b, d), (f, a), (d, c)";
		}

		char[] X = new char[(Deps.length() + 2) / 7];
		char[] Y = new char[X.length];
		for (int i = 0; i < X.length; i++) {
			X[i] = Deps.charAt(i * 8 + 1);
			Y[i] = Deps.charAt(i * 8 + 4);
		}

		char[] order = buildOrder(projs, X, Y);
		
		print("Order: ");
		for (int i = 0; i < order.length - 1; i++) {
			print(order[i] + ", ");
			if ((i + 1) % 25 == 0) {
				print("\n");
			}
		}
		print(order[order.length - 1] + "");
		sc.close();
	}

	public static char[] buildOrder(char[] projs, char[] X, char[] Y) {
		char[] order = new char[projs.length];
		int depCount = X.length;
		char[] stack = new char[projs.length];
		int stackCount = 0;
		for (int i = 0; i < projs.length; i++) {
			while (inStack(projs[i], stack, stackCount)) {
				i++;
				if (i == projs.length) {
					break;
				}
			}
			if (i == projs.length) {
				break;
			}
			int[] counts = getOrder(projs[i], Y, X, stack, depCount, stackCount);
			depCount = counts[0];
			stackCount = counts[1];
		}
		order = stack;
		return order;
	}

	public static int[] getOrder(char c, char[] Y, char[] X, char[] stack, int depCount, int stackCount) {
		char[] chars = new char[depCount];
		int count = 0;
		// get dependency indices of character c
		for (int i = 0; i < depCount; i++) {
			// need AND condition to see if X[i] has already been used in the stack
			if (c == Y[i] && !inStack(X[i], stack, stackCount)) {
				chars[count] = X[i];
				count++;
				replace(Y, Y[depCount - 1], i);
				replace(X, X[depCount - 1], i);
				depCount--;
				i--;
			}
		}
		// get dependencies of the dependencies of character c
		for (int i = 0; i < count; i++) {
			int[] counts = getOrder(chars[i], Y, X, stack, depCount, stackCount);
			depCount = counts[0];
			stackCount = counts[1];
		}
		stack[stackCount] = c;
		stackCount++;
		return new int[] { depCount, stackCount };
	}

	public static boolean inStack(char c, char[] stack, int stackCount) {
		for (int i = 0; i < stackCount; i++) {
			if (stack[i] == c) {
				return true;
			}
		}
		return false;
	}

	public static void swap(char[] chars, int a, int b) {
		char temp = chars[a];
		chars[a] = chars[b];
		chars[b] = temp;
	}

	public static void replace(char[] chars, char c, int i) {
		chars[i] = c;
	}

	// Test function
	public static void printChars(char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			print("" + chars[i]);
		}
		print("\n");
	}

	public static void print(String s) {
		System.out.print(s);
	}
}
