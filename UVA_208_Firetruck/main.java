import java.util.Scanner;
import java.util.Stack;

public class main {
	private static boolean streets[][]; // Grid of available street corners
	private static boolean reachable[][]; // Grid of corners (u,v) where v is
											// reachable from u
	private static boolean visited[]; // Corners visited by DFS, for fast lookup
	private static Stack<Integer> path; // Path to print
	private static int paths; // # of paths from corner #1 to destination corner

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = 1; // Case #
		int dest; // Corner # of destination corner
		int max; // Largest # corner in the current grid
		int u, v; // Streets u and v meet to make a corner

		// Read in input from user in format:
		// dest
		// u1 v1
		// u2 v2
		// ...
		// un vn
		// 0 0
		while (scan.hasNextInt()) { // Will continue looping until no more input
			int temp; // Placeholder for calculations

			// Reset streets, reachable, visited, path, # paths and max
			streets = new boolean[21][21];
			reachable = new boolean[21][21];
			visited = new boolean[21];
			path = new Stack<Integer>();
			paths = 0;
			max = 0;

			dest = scan.nextInt(); // Get destination corner #

			// Gets u and v until sentinel pair 0, 0 trigger end up input
			while ((u = scan.nextInt()) != 0 && (v = scan.nextInt()) != 0) {
				streets[u][v] = streets[v][u] = true;
				reachable[u][v] = reachable[v][u] = true;
				// If u or v > max, replace max with larger of u and v
				if ((temp = Math.max(u, v)) > max)
					max = temp;
			}
			System.out.printf("CASE %d:\n", cases++);
			findReachable(max);
			dfs(dest, max);
			System.out
					.printf("There are %d routes from the firestation to streetcorner %d.\n",
							paths, dest);
			scan.next(); // clears buffer for new input
		}
		System.out.println(""); // Pleases UVA Online
	}

	// If for any corners u and v, if there exists a path from u to v,
	// reachable[u][v] will be marked true. This will be useful later to save
	// time during DFS so time will not be wasted checking paths that don't
	// reach the destination
	private static void findReachable(int max) {
		for (int i = 1; i <= max; ++i)
			for (int j = 1; j <= max; ++j)
				for (int k = 1; k <= max; ++k)
					if (reachable[i][j] && reachable[j][k])
						reachable[i][k] = true;
	}

	// All routes begin from corner #1
	public static void dfs(int dest, int max) {
		dfs(1, dest, max);
	}

	// This is a modified recursive DFS algorithm that before performing a new
	// call on an available neighbor, checks to see if said neighbor has a path
	// to the destination. This cuts down on time by preventing the program from
	// pursuing paths that will eventually result in a failure to find the
	// destination corner.
	private static void dfs(int start, int dest, int max) {
		// Add valid neighbor to path and mark as visited
		path.push(start);
		visited[start] = true;

		// If destination has been reached, print path and begin unwrapping
		// recursive DFS calls
		if (start == dest) {
			printPath();
			paths++;
		} else {
			for (int i = 1; i <= max; ++i)
				// Check for valid neighbor and if the neighbor can reach dest
				if (streets[start][i] && reachable[i][dest] && !visited[i]) {
					dfs(i, dest, max);
				}
		}
		// Remove neighbor from path and mark not visted before returning to
		// previous DFS call
		path.pop();
		visited[start] = false;
	}

	// Prints current path in format conforming to prompt from UVA Online Judge
	private static void printPath() {
		StringBuilder strPath = new StringBuilder();
		for (int n : path)
			strPath.append(n + " ");
		strPath.deleteCharAt(strPath.length() - 1);
		System.out.println(strPath.toString());
	}
}
