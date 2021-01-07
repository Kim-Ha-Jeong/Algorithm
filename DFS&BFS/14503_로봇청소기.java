import java.util.*;
import java.io.*;

public class BOJ_14503 {
	static int n, m, arr[][];
	static boolean visited[][];
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };
	static int cnt = 0;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st[] = br.readLine().split(" ");
		int r, c, d;
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		arr = new int[n][m];
		visited = new boolean[n][m];

		st = br.readLine().split(" ");
		r = Integer.parseInt(st[0]);
		c = Integer.parseInt(st[1]);
		d = Integer.parseInt(st[2]);

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(s[j]);

		}

		bfs(r, c, d);

		count();

		System.out.println(cnt);

	}

	static void bfs(int r, int c, int d) {
		Queue<Section> q = new LinkedList<>();
		q.add(new Section(r, c, d));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Section now = q.poll();

			Section after = select(now);

			if (after.r == -1)
				break;

			q.add(after);
		}

	}

	public static Section select(Section now) {
		int r = now.r;
		int c = now.c;
		int d = now.d;
		int nr, nc;

		for (int i = 0; i < 4; i++) {
			d = (d + 3) % 4;
			nr = r + dr[d];
			nc = c + dc[d];

			if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
				if (arr[nr][nc] == 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					return new Section(nr, nc, d);
				}
			}
		}

		nr = r + dr[(d + 2) % 4];
		nc = c + dc[(d + 2) % 4];

		if (arr[nr][nc] == 1)
			return new Section(-1, -1, -1);

		visited[nr][nc] = true;
		return new Section(nr, nc, d);
	}

	public static void count() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j])
					cnt++;
			}
		}
	}
}

class Section {
	int r;
	int c;
	int d;

	Section(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}
