import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2580 {
	static int[][] map = new int[9][9];
	static ArrayList<Node> zero;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		zero = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 0) {
					zero.add(new Node(i, j));
				}
			}
		}

		dfs(0);
	}

	static void dfs(int idx) {
		if (idx == zero.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}

		Node cur = zero.get(idx);
		for (int i = 1; i < 10; i++) {
			if (isRight(cur.x, cur.y, i)) {
				map[cur.x][cur.y] = i;
				dfs(idx + 1);
				map[cur.x][cur.y] = 0;
			}
		}
	}

	static boolean isRight(int x, int y, int val) {
		if (map[x][y] != 0)
			return false;

		for (int i = 0; i < 9; i++) {
			if (map[x][i] == val || map[i][y] == val) {
				return false;
			}
		}

		int rX = (x / 3) * 3;
		int rY = (y / 3) * 3;

		for (int i = rX; i < rX + 3; i++) {
			for (int j = rY; j < rY + 3; j++) {
				if (map[i][j] == val) {
					return false;
				}
			}
		}

		return true;
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
