import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class Graph_11 {
	static boolean[][] visited;
	static int n;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[] cnt = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][n];
		char[][] abnormalMap = new char[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				char tmp = s[j].charAt(0);
				
				if ('G' == tmp) {
					abnormalMap[i][j] = 'R';
					continue;
				}

				abnormalMap[i][j] = tmp;
				map[i][j] = tmp;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map);
					cnt[0]++;
				}
			}
		}

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j, abnormalMap);
					cnt[1]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt[0]);
		sb.append(" ");
		sb.append(cnt[1]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(int x, int y, char[][] map) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;

				if (!visited[nx][ny] && map[nx][ny] == map[cur.x][cur.y]) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				}

			}
		}
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
