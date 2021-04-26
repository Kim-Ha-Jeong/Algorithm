import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_6 {
	static int sticker[][], n, dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		while (tc > 0) {
			n = Integer.parseInt(br.readLine());
			sticker = new int[2][n + 1];
			dp = new int[2][n + 1];

			for (int i = 0; i < 2; i++) {
				String s[] = br.readLine().split(" ");
				for (int j = 1; j < n + 1; j++)
					sticker[i][j] = Integer.parseInt(s[j - 1]);
			}

			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];

			System.out.println(getMax());
			
			tc--;
		}

	}

	static int getMax() {
		for (int j = 2; j < n + 1; j++) {
			dp[0][j] = Math.max(dp[1][j - 2], dp[1][j - 1]) + sticker[0][j];
			dp[1][j] = Math.max(dp[0][j - 2], dp[0][j - 1]) + sticker[1][j];
		}

		return Math.max(dp[0][n], dp[1][n]);
	}
}
