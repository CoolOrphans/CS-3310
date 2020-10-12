import java.util.Scanner;

public class MidtermProject2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		print("Enter number the size of the random numbers: ");
		int size = sc.nextInt();
		while (size < 2) {
			print("Input should be greater than or equal to 2. Enter the size: ");
			size = sc.nextInt();
		}
		int[] arr = createArray(size, -100, 100);

		print("Enter the sum: ");
		int sum = sc.nextInt();
		while (sum > 200 || sum < -200) {
			print("Input should be between [-200, 200]. Enter the sum: ");
			sum = sc.nextInt();
		}

		if (size <= 100) {
			println("\nUnsorted Array (index, number)");
			printArray(arr);
			newline();
		}

		long[] time = new long[5];

		time[1] = System.nanoTime();
		int[] vals1 = bruteForce(arr, sum);
		time[1] = System.nanoTime() - time[1];

		time[2] = System.nanoTime();
		int[] vals2 = backtracking(arr, sum);
		time[2] = System.nanoTime() - time[2];

		heapSort(arr);

		if (size <= 100) {
			println("\nSorted Array (index, number)");
			printArray(arr);
			newline();
		}

		time[3] = System.nanoTime();
		int[] vals3 = binarySearch(arr, sum);
		time[3] = System.nanoTime() - time[3];

		time[4] = System.nanoTime();
		int[] vals4 = interpolationSearch(arr, sum);
		time[4] = System.nanoTime() - time[4];

		println("\nUnsorted");
		print("Brute Force:          " + time[1] + " ns. ");
		printValues(vals1, sum);
		print("Backtracking:         " + time[2] + " ns. ");
		printValues(vals2, sum);

		println("Sorted");
		print("Binary Search:        " + time[3] + " ns. ");
		printValues(vals3, sum);
		print("Interpolation Search: " + time[4] + " ns. ");
		printValues(vals4, sum);

		sc.close();
	}

	// tests all combinations
	public static int[] bruteForce(int[] arr, int sum) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (sum == arr[i] + arr[j]) {
					return new int[] { arr[i], arr[j], i, j };
				}
			}
		}
		return new int[0];
	}

	// tests a number only if there's a possibility to add to sum
	public static int[] backtracking(int[] arr, int sum) {
		for (int i = 0; i < arr.length - 1; i++) {
			// not possible for value to reach sum so "backtracks" to a different index
			while (Math.abs(arr[i]) + 100 < Math.abs(sum)) {
				i++;
				// no more values in array
				if (i == arr.length) {
					return new int[0];
				}
			}
			for (int j = i + 1; j < arr.length; j++) {
				if (sum == arr[i] + arr[j]) {
					return new int[] { arr[i], arr[j], i, j };
				}
			}
		}
		return new int[0];
	}

	// tests a random number and uses the sorted array to find the second number
	public static int[] binarySearch(int[] arr, int sum) {
		int[] indices = new int[arr.length];
		int r;
		for (int i = 0; i < arr.length; i++) {
			int[] vals = pickRandom(indices, i, sum, arr);
			if (vals.length == 0) {
				return vals;
			}
			i = vals[0];
			r = vals[1];

			int j = binary(arr, r, 0, arr.length - 1, sum);
			if (j != -1) {
				return new int[] { arr[r], arr[j], r, j };
			}
		}
		return new int[0];
	}

	public static int binary(int[] arr, int i, int low, int high, int sum) {
		if (high >= low && low <= arr.length - 1) {
			int m = (high - low) / 2 + low;
			int v = arr[i] + arr[m];
			if (v == sum) {
				if (i != m) {
					return m;
					// check left and right indices
				} else if (m + 1 < arr.length && arr[i] == arr[m + 1]) {
					return m + 1;
				} else if (m - 1 >= 0 && arr[i] == arr[m - 1]) {
					return m - 1;
				} else {
					return -1;
				}
			} else if (v > sum) {
				return binary(arr, i, low, m - 1, sum);
			} else {
				return binary(arr, i, m + 1, high, sum);
			}
		}
		return -1;
	}

	// picks random index from array
	public static int[] pickRandom(int[] indices, int i, int sum, int[] arr) {
		int r;
		boolean repeat = false;
		do {
			repeat = false;
			r = random(0, arr.length - 1);
			// not possible for value to reach sum
			if (Math.abs(arr[r]) + 100 < Math.abs(sum)) {
				repeat = true;
				indices[i] = r;
				i++;
				// no more values in array
				if (i == arr.length) {
					return new int[0];
				}
			} else {
				// check if number has been used
				for (int j = 0; j < i; j++) {
					if (r == indices[j]) {
						// same random number
						repeat = true;
						break;
					}
				}
			}
		} while (repeat);
		indices[i] = r;
		return new int[] { i, r };
	}

	// picks an pivot point based off the fact that the sorted array has a uniform
	// distribution of numbers
	public static int[] interpolationSearch(int[] arr, int sum) {
		int low = 0;
		int high = arr.length - 1;
		int r;
		int[] indices = new int[arr.length];
		for (int i = 0; i < arr.length - 1; i++) {
			int[] vals = pickRandom(indices, i, sum, arr);
			if (vals.length == 0) {
				return vals;
			}
			i = vals[0];
			r = vals[1];
			int j = sum - arr[r];
			j = interpolate(arr, low, high, j);
			if (j != -1) {
				return new int[] { arr[r], arr[j], r, j };
			}
		}
		return new int[0];
	}

	public static int interpolate(int[] arr, int low, int high, int x) {
		if (low <= high && x >= arr[low] && x <= arr[high]) {
			int m = low + ((x - arr[low]) * (high - low) / (arr[high] - arr[low]));
			if (arr[m] == x) {
				return m;
			} else if (arr[m] < x) {
				return interpolate(arr, m + 1, high, x);
			} else if (arr[m] > x) {
				return interpolate(arr, low, m - 1, x);
			}
		}
		return -1;
	}

	// picks two indices based off position in array and with a large enough
	// array they will most likely result in the numbers at the indices adding to
	// the sum
	// works initially like a greedy algorithm
	public static int[] percentageTechnique(int[] arr, int sum) {
		int x = pickOptimal(arr, sum);
		int y = pickOptimal(arr, sum - arr[x]);
		// duplicate indices
		if (y == x) {
			if (y < arr.length - 1) {
				y++;
			} else {
				y--;
			}
		}
		if (arr[x] + arr[y] == sum) {
			return new int[] { arr[x], arr[y], x, y };
		} else {
			// divide and conquer since array is sorted
			return binarySearch(arr, sum);
		}
	}

	// picks optimal index from sorted array which is good for large arrays
	public static int pickOptimal(int[] arr, int sum) {
		if (sum >= 100) {
			return (arr.length - 1);
		} else if (sum <= -100) {
			return 0;
		} else {
			double perc = (double) sum / 200 + .5;
			return ((int) (arr.length * perc));
		}
	}

	public static void printValues(int[] vals, int sum) {
		if (vals.length == 0) {
			println("No two numbers in the array add to the sum");
		} else {
			println(vals[0] + " and " + vals[1] + " add to " + sum + " with indices of " + vals[2] + " and " + vals[3]
					+ " respectively");
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			print("(" + i + "," + arr[i] + "), ");
			if ((i + 1) % 10 == 0) {
				newline();
			}
		}
		print("(" + (arr.length - 1) + "," + arr[arr.length - 1] + ")");
	}

	public static void heapSort(int arr[]) {
		buildHeap(arr);
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapify(arr, 0, i);
		}
	}

	public static void buildHeap(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
	}

	public static void heapify(int arr[], int i, int heapSize) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left < heapSize && arr[left] > arr[largest]) {
			largest = left;
		}
		if (right < heapSize && arr[right] > arr[largest]) {
			largest = right;
		}
		if (largest != i) {
			swap(arr, i, largest);
			heapify(arr, largest, heapSize);
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static int[] createArray(int size, int a, int b) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = random(a, b);
		}
		return arr;
	}

	public static int random(int a, int b) {
		return ((int) (((b - a + 1) * Math.random())) + a);
	}

	public static void newline(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println();
		}
	}

	public static void newline() {
		System.out.println();
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void println(String s) {
		System.out.println(s);
	}

}
