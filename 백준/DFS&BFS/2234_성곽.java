import java.io.*;
import java.util.*;

public class BOJ_2234 {
	static int n, m, map[][];
	static boolean visited[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		map = new int[m][n];
		visited = new boolean[m][n];
		int room = 0;
		int max = -1;

		for (int i = 0; i < m; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(s[j]);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					room++;
					max = Math.max(bfs(i, j), max);
				}
			}
		}

		System.out.println(room);
		System.out.println(max);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				for (boolean a[] : visited)
					Arrays.fill(a, false);

				for (int k = 1; k <= 8; k *= 2) {
					if ((map[i][j] & k) == k) {
						visited[i][j] = true;

						map[i][j] = map[i][j] - k;
						max = Math.max(max, bfs(i, j));
						map[i][j] = map[i][j] + k;
					}
				}
			}
		}

		System.out.println(max);
	}

	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		int cnt = 0;
		q.add(new Node(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Node now = q.poll();
			cnt++;
			String b = Integer.toBinaryString(map[now.x][now.y]);

			if (b.length() < 4) {
				for (int i = b.length(); i < 4; i++)
					b = '0' + b;
			}

			for (int i = 0; i < 4; i++) {
				if (b.charAt(3 - i) == '1')
					continue;
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= m || ny >= n)
					continue;

				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				}

			}
		}

		return cnt;
	}

}
