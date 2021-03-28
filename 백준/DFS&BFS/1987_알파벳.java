import java.io.*;
import java.util.*;

public class BOJ_1987 {
	static char board[][];
	static int r, c;
	static boolean visited[];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");
		r = Integer.parseInt(st[0]);
		c = Integer.parseInt(st[1]);

		board = new char[r][c];
		visited = new boolean[26];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++)
				board[i][j] = s.charAt(j);
		}

		dfs(0, 0, 0);
		
		System.out.println(result);

	}

	static void dfs(int x, int y, int cnt) {
		if (visited[board[x][y] - 'A']) {
			result = Math.max(result, cnt);
			return;
		} else {
			visited[board[x][y] - 'A'] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < r && ny >= 0 && ny < c)
					dfs(nx, ny, cnt + 1);

			}

			visited[board[x][y] - 'A'] = false;
		}
	}
}
