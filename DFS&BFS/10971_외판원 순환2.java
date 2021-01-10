import java.util.*;
import java.io.*;

public class BOJ_10971 {
	static int W[][];
	static boolean visited[];
	static int n, start;
	static int sum = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		W = new int[n][n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++)
				W[i][j] = Integer.parseInt(s[j]);
		}

		for (int i = 0; i < n; i++) {
			start = i;
			dfs(0, i, 0);
			clean();
		}

		System.out.println(min);
	}

	public static void dfs(int index, int row, int sum) {
		if (index == n && start == row) {
			if(min > sum) min = sum;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (W[row][i] == 0)
				continue;

			if (!visited[i] && W[row][i] > 0) {
				visited[i] = true;
				sum += W[row][i];

				if (sum <= min)
					dfs(index + 1, i, sum);

				visited[i] = false;
				sum -= W[row][i];
			}

		}

	}

	public static void clean() {
		for (int i = 0; i < n; i++)
			visited[i] = false;
	}

}
