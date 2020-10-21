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
		char[] deps = new char[(Deps.length() + 2) / 7 * 2];
		int k = 0;
		for (int i = 0; i < deps.length / 2; i++) {
			deps[k] = Deps.charAt(i * 8 + 1);
			deps[k + 1] = Deps.charAt(i * 8 + 4);
			k += 2;
		}

		// NOTE COULD HAVE TWO SEPARATE ARRAYS OF X's AND Y's.
		char[] x = new char[(Deps.length() + 2) / 7];
		char[] y = new char[x.length];
		for (int i = 0; i < x.length; i++) {
			x[i] = Deps.charAt(i * 8 + 1);
			y[i] = Deps.charAt(i * 8 + 4);
		}
		printChars(x);
		printChars(y);

		sc.close();
	}

	// to remove dependencies can swap characters at index and decrease a counter
	public static void buildOrder(char[] projs, char[] x, char[] y) {
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
