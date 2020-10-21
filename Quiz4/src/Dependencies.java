import java.util.Scanner;

public class Dependencies {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		print("Projects: ");
		// EXAMPLE INPUT: a, b, c, d, e, f
		String Projs = sc.next();
		char[] projs = new char[(Projs.length() + 2) / 3];
		for (int i = 0; i < projs.length; i++) {
			projs[i] = Projs.charAt(i * 3);
		}
		print("\nDependencies: ");

		sc.close();

	}

	public static void createDependency(char a, char b) {

	}

	public static void print(String s) {
		System.out.print(s);
	}
}
