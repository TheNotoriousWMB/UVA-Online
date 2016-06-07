import java.util.HashMap;
import java.util.Scanner;

public class main {
	private static HashMap<String, Integer> solutions;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Get input in format
		// n
		// board_1
		// ...
		// board_n
		int lines = scan.nextInt();

		for (int i = lines; i > 0; --i) {
			solutions = new HashMap<String, Integer>();
			System.out.println(solve(scan.next()));
		}
	}

	public static int solve(String s) {
		int count = 0;

		if (solutions.containsKey(s))
			return solutions.get(s);

		// Initialize count for this instance of solve() to be the number of
		// pebbles currently on the board
		for (int i = 0; i < s.length(); ++i)
			if (s.charAt(i) == 'o')
				count++;

		// Scan the current board until a '-' is found. Then check the two neighbors
		// to the left, and the two neighbors to the right for 'o' characters.
		// If either of these tests pass, swap the furthermost 'o' into index
		// i and set the middle space to '-'. Then recursively call solve() on
		// this new board.
		for (int i = 0; i < s.length(); ++i) {
			// Clone current board for manipulation
			StringBuilder clone = new StringBuilder(s);

			if (clone.charAt(i) == '-') {
				// Prevent out of bounds exception via negative indices
				if (i >= 2)
					// if sequence 'oo-' found, swap to '--o' and call solve()
					// on new board.
					if (clone.charAt(i - 2) == 'o'
							&& clone.charAt(i - 1) == 'o') {
						clone.setCharAt(i, 'o');
						clone.setCharAt(i - 1, '-');
						clone.setCharAt(i - 2, '-');

						// Compare native count to result of recursive call
						// and save the smaller number.
						count = Math.min(count, solve(clone.toString()));
						// Restore clone to copy of s
						clone = new StringBuilder(s);
					} else {
					}
				// Prevent out of bounds exception beyond dimension of the string
				if (i + 2 < s.length()) {
					// if sequence '-oo' found, swap to 'o--' and call solve()
					// on new board.
					if (clone.charAt(i + 2) == 'o'
							&& clone.charAt(i + 1) == 'o') {
						clone.setCharAt(i, 'o');
						clone.setCharAt(i + 1, '-');
						clone.setCharAt(i + 2, '-');
						
						// Compare native count to result of recursive call
						// and save the smaller number.
						count = Math.min(count, solve(clone.toString()));
					}
				}
			}
		}

		// Add solution for current board to HashMap
		solutions.put(s, count);
		return (solutions.get(s));
	}
}
