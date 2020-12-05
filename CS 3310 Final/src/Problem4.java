import java.util.Scanner;

public class Problem4 {
	// 4) Given an NxN matrix of positive and negative integers, find the sub-matrix
	// with the largest possible sum.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		print("Enter size of N x N matrix: ");
		int n = sc.nextInt();
		int[][] matrix = createMatrix(n, -99, 99);
		print("\nMatrix\n");
		printMatrix(matrix, 0, 0, n - 1, n - 1);
		int[] s = effBF(matrix);
		print("\nOutput: " + s[0]);
		print("\nSubmatrix\n");
		printMatrix(matrix, s[1], s[2], s[3], s[4]);
		sc.close();
	}

	public static int[] effBF(int[][] m) {
		// s[0] = largest, s[1] = top left x, s[2] = top left y, s[3] = bottom right x,
		int[] s = new int[5];
		int n = m.length;
		int largest = Integer.MIN_VALUE;
		int x = 0;
		int y = 0;
		int sum;
		for (int i = 0; i < n * n - 1; i++) {
			if (i % n == 0 && i != 0) {
				x = 0;
				y++;
			}
			// sums contains the current sums for the sub matrices from (x, y) to (x1, y1)
			int[][] sums = new int[n + 1][n + 1];
			sums[x + 1][y + 1] = m[x][y];
			for (int x1 = x; x1 < n; x1++) {
				for (int y1 = y; y1 < n; y1++) {
					sum = sums[x1][y1 + 1] + sums[x1 + 1][y1] - sums[x1][y1] + m[x1][y1];
					sums[x1 + 1][y1 + 1] = sum;
					if (sum > largest) {
						s[0] = sum;
						s[1] = x;
						s[2] = y;
						s[3] = x1;
						s[4] = y1;
						largest = sum;
					}
				}
			}
			x++;
		}
		return s;
	}

	public static void printMatrix(int[][] m, int x1, int y1, int x2, int y2) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				print(String.format("%1$3s", m[i][j]) + " ");
			}
			print("\n");
		}
	}

	public static int[][] createMatrix(int n, int l, int h) {
		int[][] m = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				m[i][j] = random(l, h);
			}
		}
		return m;
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
