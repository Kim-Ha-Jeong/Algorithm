import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_5 {
	static int dp[][], map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int row, col;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s[] = br.readLine().split(" ");

		row = Integer.parseInt(s[0]);
		col = Integer.parseInt(s[1]);

		dp = new int[row][col];
		map = new int[row][col];

		for (int i = 0; i < row; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				dp[i][j] = -1;
			}
		}

		System.out.println(down(0, 0));
	}

	static int down(int x, int y) {
		if (x == row - 1 && y == col - 1)
			return 1;
		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= row || ny >= col || nx < 0 || ny < 0)
				continue;
			if (map[x][y] > map[nx][ny])
				dp[x][y] += down(nx, ny);
		}
		
		return dp[x][y];
	}

}
