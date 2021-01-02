package study;

import java.util.*;

public class SafetyZone {
	static int n;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];

		int max = 0, count = 0;
		int result = 1;
		int min = 101;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				max = Math.max(max, arr[i][j]);
				min = Math.min(min, arr[i][j]);
			}
		}

		for (int h = min; h < max + 1; h++) {
			visited = new boolean[n][n];
			count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] > h && !visited[i][j]) {
						count++;
						safe(i, j, h);
					}
				}
			}
			result = Math.max(result, count);
		}

		System.out.println(result);
	}

	public static void safe(int x, int y, int height) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if (!visited[nx][ny] && arr[nx][ny] > height)
					safe(nx, ny, height);
			}
		}
	}
}
