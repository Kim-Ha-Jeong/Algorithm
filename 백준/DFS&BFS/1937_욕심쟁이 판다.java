import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_1937 {
	static int[][] dp, arr;
	static int n;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n][n];
		arr = new int[n][n];
		
		int ret = 0;
		int x = 0, y = 0;
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ret = Math.max(dfs(i, j), ret);
			}
		}
		
		bw.write(Integer.toString(ret));
		bw.flush();
		bw.close();
	}
	
	static int dfs(int x, int y) {
		if(dp[x][y] != 0) return dp[x][y];
		
		dp[x][y] = 1;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny>= n)
				continue;
			
			if(arr[x][y] < arr[nx][ny]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
			}
		}
		
		return dp[x][y];
	}

}
