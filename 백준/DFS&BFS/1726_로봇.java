import java.io.*;
import java.util.*;

public class BOJ_1726 {
	static int m, n, map[][];
	static Point start, end;
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st[] = br.readLine().split(" ");

		m = Integer.parseInt(st[0]);
		n = Integer.parseInt(st[1]);

		map = new int[m + 1][n + 1];
		visited = new boolean[m + 1][n + 1][5];

		for (int i = 1; i < m + 1; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 1; j < n + 1; j++)
				map[i][j] = Integer.parseInt(s[j - 1]);
		}

		for (int i = 0; i < 2; i++) {
			String s[] = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			int d = Integer.parseInt(s[2]);

			if (i == 0)
				start = new Point(x, y, d, 0);
			else
				end = new Point(x, y, d, 0);
		}

		bfs();
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y][start.d] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (end.x == now.x && end.y == now.y && end.d == now.d) {
				System.out.println(now.cnt);
				return;
			}

			for (int i = 1; i <= 3; i++) {
				int nx = now.x + dx[now.d - 1] * i;
				int ny = now.y + dy[now.d - 1] * i;

				if (nx <= 0 || nx > m || ny <= 0 || ny > n)
					continue;

				if (map[nx][ny] == 0) {
					if (!visited[nx][ny][now.d]) {
						visited[nx][ny][now.d] = true;
						q.add(new Point(nx, ny, now.d, now.cnt + 1));
					}
				} else
					break;
			}

			for (int i = 1; i <= 4; i++) {
				if (now.d != i && !visited[now.x][now.y][i]) {
					visited[now.x][now.y][i] = true;
					if ((now.d == 1 && i == 2) || (now.d == 2 && i == 1) || (now.d == 3 && i == 4)
							|| (now.d == 4 && i == 3))
						q.add(new Point(now.x, now.y, i, now.cnt + 2));
					else
						q.add(new Point(now.x, now.y, i, now.cnt + 1));
				}

			}
		}

	}

	static class Point {
		int x;
		int y;
		int d;
		int cnt;

		Point(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
	}

}
