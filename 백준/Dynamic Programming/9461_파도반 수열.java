import java.io.*;
public class DP_7 {
	static long dp[] = new long[101];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wave();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc > 0) {
			int n = Integer.parseInt(br.readLine());
			
			System.out.println(dp[n]);
			
			tc--;
		}
	}

	public static void wave() {
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i=4; i<101; i++) 
			dp[i] = dp[i-3]+dp[i-2];
		
	}
}
