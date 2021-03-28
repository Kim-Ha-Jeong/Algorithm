import java.util.*;
import java.io.*;

public class BOJ_2206 {
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int n, m, map[][], dist[][][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		map = new int[n][m];
		dist = new int[n][m][2];

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split("");
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(s[j]);
		}

		breakWall();

	}

	static void breakWall() {
		int res = -1;
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(0, 0, 0, 0));
		dist[0][0][0] = 0;

		while (!q.isEmpty()) {
			Node d = q.poll();

			if (d.x == n - 1 && d.y == m - 1) {
				res = dist[d.x][d.y][d.w];
				if(res != -1)
					res++;
				break;
			}

			if (d.w < 1) {
				for (int i = 0; i < 4; i++) {
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;

					if (map[nx][ny] == 1 && dist[nx][ny][d.w + 1] == 0) {
						dist[nx][ny][d.w+1] = d.cnt + 1;
						q.add(new Node(nx, ny, d.w + 1, d.cnt + 1));
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;

				if (map[nx][ny] != 1 && dist[nx][ny][d.w] == 0) {
					dist[nx][ny][d.w] = d.cnt + 1;
					q.add(new Node(nx, ny, d.w, d.cnt + 1));
				}
			}
		}

		System.out.println(res);

	}

	static class Node {
		int x;
		int y;
		int w;
		int cnt;

		Node(int x, int y, int w, int cnt) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.cnt = cnt;
		}
	}
}
