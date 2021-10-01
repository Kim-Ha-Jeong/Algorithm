import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class BOJ_17070 {
	static int[][] map;
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		dp = new int[n+1][n+1][3];
		dp[1][2][0] = 1;
		
		for(int i=1; i<n+1; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				for(int k=0; k<3; k++) {
					if(dp[i][j][k] == 0) continue;

					if((k == 0 || k == 2) && j+1 < n+1) {
						if(map[i][j+1] != 1) {
							dp[i][j+1][0] += dp[i][j][k];
						}
					} 
					
					if((k == 1 || k == 2) && i+1 < n+1) {
						if(map[i+1][j] != 1) {
							dp[i+1][j][1] += dp[i][j][k];
						}
					} 
					
					if(i+1 < n+1 && j+1 < n+1) {
						if(map[i+1][j+1] != 1 && map[i][j+1] != 1 && map[i+1][j] != 1) {
							dp[i+1][j+1][2] += dp[i][j][k];
						}
					}
					
				}
			}
		}
		
		int ret = 0;
		for(int i=0; i<3; i++) {
			ret += dp[n][n][i];
		}
		
		bw.write(ret+"");
		bw.flush();
		bw.close();
	}
}
