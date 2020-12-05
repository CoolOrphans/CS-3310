import java.util.Scanner;

public class Problem2 {
	// 2) You are given an array of integers (both positive and negative values).
	// Find the contiguous sequence with the largest sum.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		print("Input: ");
		// Example input: "2, -8, 3, -2, 4, -10"
		String input = sc.next();
		// Use enter to key to get the default input
		if (input.length() == 1) {
			input = "2, -8, 3, -2, 4, -10\n";
		}
		int size = getInputSize(input);
		int[] numbers = new int[size];
		for (int i = 0; i < size - 1; i++) {
			numbers[i] = Integer.valueOf(input.substring(0, input.indexOf(',')));
			input = input.substring(input.indexOf(' ') + 1);
		}
		input = input.substring(input.indexOf(' ') + 1);
		numbers[size - 1] = Integer.valueOf(input.substring(input.indexOf(' ') + 1, input.length() - 1));
		int[] seq = bruteForce(numbers);
		print("Output: " + seq[0] + " which is the sequence [");
		for (int i = seq[1]; i < seq[2]; i++) {
			print(numbers[i] + ", ");
		}
		print(numbers[seq[2]] + "]");
		sc.close();
	}

	public static int[] bruteForce(int[] numbers) {
		// seq[0] = largest, seq[1] = start, seq[2] = end
		int[] seq = new int[3];
		int n = numbers.length;
		int largest = Integer.MIN_VALUE;
		// not checking last one since it wouldn't make a sequence
		for (int i = 0; i < n - 1; i++) {
			int sum = numbers[i];
			for (int j = i + 1; j < n; j++) {
				sum += numbers[j];
				if (sum > largest) {
					seq[0] = sum;
					seq[1] = i;
					seq[2] = j;
					largest = sum;
				}
			}
		}

		return seq;
	}

	public static int getInputSize(String s) {
		int size = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',') {
				i += 2;
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

	public static int random(int a, int b) {
		return ((int) (((b - a + 1) * Math.random())) + a);
	}

}
