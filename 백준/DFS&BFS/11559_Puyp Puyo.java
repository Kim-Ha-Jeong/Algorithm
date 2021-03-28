import java.io.*;
import java.util.*;

public class BOJ_11559 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int n = 12, m = 6;
	static char[][] map;
	static boolean[][] visited;
	static int crush_count = 0;
	static int total = 0;
	static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = str[j];
			}
		}

		while (true) {
			// 매번 마다 새롭게 visited를 초기화 해줘야 한다.
			visited = new boolean[n][m];
			crush_count = 0;
			for (int i = n - 1; i >= 0; i--) {
				for (int j = m - 1; j >= 0; j--) {
					if (map[i][j] != '.' && !visited[i][j]) {
						bfs(new dot(i, j));
					}
				}
			}
			// 더 이상 터질 것이 없으면 break 한다.
			if (crush_count == 0) {
				break;
			} else {
				total++;
			}
			// 남은 뿌요들을 아래로 내리기
			down();
		}

		System.out.println(total);
	}

	public static void bfs(dot d) {
		char check = map[d.x][d.y];
		int count = 0;
		Queue<dot> q = new LinkedList<dot>();
		ArrayList<dot> save = new ArrayList<dot>();
		visited[d.x][d.y] = true;
		q.add(d);

		while (!q.isEmpty()) {
			dot t = q.poll();
			save.add(t);
			count++;

			for (int i = 0; i < 4; i++) {
				int x1 = t.x + dx[i];
				int y1 = t.y + dy[i];

				if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < m && map[x1][y1] == check && !visited[x1][y1]) {
					visited[x1][y1] = true;
					q.add(new dot(x1, y1));
				}
			}
		}
		// 연결된 것이 4개가 넘으면 save의 뿌요들을 .으로 바꾸고 crush_count를 증가시킨다.
		if (count >= 4) {
			crush_count++;
			for (int i = 0; i < save.size(); i++) {
				dot tmp = save.get(i);
				map[tmp.x][tmp.y] = '.';
			}
		}

	}

	// .이 아니라면 goGround함수 실행
	public static void down() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (map[i][j] != '.') {
					goGround(i, j);
				}
			}
		}
	}

	// 한 열에서 가장 밑에 .이 나오는 인덱스 t를 찾고 원래의 dot(a,b) 와 교환해준다.
	public static void goGround(int a, int b) {
		int t = -1;

		for (int i = n - 1; i > a; i--) {
			if (map[i][b] == '.') {
				t = i;
				break;
			}
		}

		if (t != -1) {
			char tmp = map[a][b];
			map[a][b] = map[t][b];
			map[t][b] = tmp;
		}
	}
}

class dot {
	int x;
	int y;

	public dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
