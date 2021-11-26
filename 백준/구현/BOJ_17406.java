import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17406 {
	static int[][] map;
	static int[][] op;
	static int n, m, k;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		map = new int[n + 1][m + 1];

		op = new int[k][3];

		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		for (int i = 0; i < k; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				op[i][j] = Integer.parseInt(s[j]);
			}
		}

		visited = new boolean[k];
		result = new int[k];
		dfs(0);

		System.out.println(min);
	}

	static void dfs(int cnt) {
		if (cnt == k) {
			int[][] copy = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					copy[i][j] = map[i][j];
				}
			}
			findMin(copy);
			return;
		}

		for (int i = 0; i < k; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[cnt] = i;
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
	}

	static void findMin(int[][] copy) {
		for (int i = 0; i < result.length; i++) {
			int idx = result[i];
			int lx = op[idx][0] - op[idx][2] - 1;
			int ly = op[idx][1] - op[idx][2] - 1;
			int rx = op[idx][0] + op[idx][2] - 1;
			int ry = op[idx][1] + op[idx][2] - 1;
			rotate(lx, ly, rx, ry, copy);
		}
		cal(copy);
	}

	static void cal(int[][] copy) {
		for (int i = 0; i < copy.length; i++) {
			int sum = 0;
			for (int j = 0; j < copy[i].length; j++) {
				sum += copy[i][j];
			}
			min = Math.min(sum, min);
		}
	}

	static void rotate(int lx, int ly, int rx, int ry, int[][] copy) {
		if (lx == rx && ly == ry)
			return;

		int[] tmp = new int[3];
		tmp[0] = copy[lx][ry];
		tmp[1] = copy[rx][ry];
		tmp[2] = copy[rx][ly];

		for (int i = ry; i > ly; i--) {
			copy[lx][i] = copy[lx][i - 1];
		}

		for (int i = rx; i > lx; i--) {
			if (i == lx + 1)
				copy[i][ry] = tmp[0];
			else
				copy[i][ry] = copy[i - 1][ry];
		}

		for (int i = ly; i < ry; i++) {
			if (i == ry - 1)
				copy[rx][i] = tmp[1];
			else
				copy[rx][i] = copy[rx][i + 1];
		}

		for (int i = lx; i < rx; i++) {
			if (i == rx - 1)
				copy[i][ly] = tmp[2];
			else
				copy[i][ly] = copy[i + 1][ly];
		}

		rotate(lx + 1, ly + 1, rx - 1, ry - 1, copy);
	}
}
