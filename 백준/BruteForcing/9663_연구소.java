package study;

import java.util.*;

public class Laboratory {
	static int n, m;
	static int map[][], copyMap[][];
	static int max = 0;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				map[i][j] = sc.nextInt();
		}

		lab(0);
		System.out.println(max);
	}

	static void lab(int index) {
		if (index == 3) {

			check();

			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (copyMap[i][j] == 0)
						sum++;
				}
			}

			if (max < sum)
				max = sum;

		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 1;
						lab(index + 1);
						map[i][j] = 0;
					}
				}
			}
		}
	}

	static void check() {
		copyMap = new int[n][m];
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				copyMap[i][j] = map[i][j];
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if (copyMap[i][j] == 2)
					q.add(new Node(i, j));
		}

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x, y;

			for (int i = 0; i < 4; i++) {
				x = node.x + dx[i];
				y = node.y + dy[i];
				if (x >= 0 && y >= 0 && x < n && y < m) {
					if (copyMap[x][y] == 0) {
						copyMap[x][y] = 2;
						q.add(new Node(x, y));
					}
				}
			}
		}
	}

}

class Node {
	int x;
	int y;

	Node(int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}
}
