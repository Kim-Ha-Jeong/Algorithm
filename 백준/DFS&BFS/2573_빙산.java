import java.io.*;
import java.util.*;

public class BOJ_2573 {
	static int n, m;
	static int arr[][], copy[][];
	static boolean visited[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		arr = new int[301][301];
		visited = new boolean[301][301];
		copy = new int[301][301];

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
				copy[i][j] = arr[i][j];
			}
		}

		int year = 0;
		while (true) {

			if (check())
				break;

			if (oneCheck()) {
				System.out.println(0);
				return;
			}

			copy();

			year++;
		}

		System.out.println(year);
	}

	public static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Node d = q.poll();
			int melt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (arr[nx][ny] == 0)
						melt++;
					if (arr[nx][ny] != 0 && !visited[nx][ny]) {
						q.add(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}

			if (arr[d.x][d.y] - melt <= 0)
				copy[d.x][d.y] = 0;
			else
				copy[d.x][d.y] -= melt;
		}

	}

	public static void copy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = copy[i][j];
				visited[i][j] = false;
			}
		}
	}

	public static boolean check() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) {
					count++;
					bfs(i, j);

					if (count >= 2)
						return true;
				}
			}
		}

		return false;
	}

	public static boolean oneCheck() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

}

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
