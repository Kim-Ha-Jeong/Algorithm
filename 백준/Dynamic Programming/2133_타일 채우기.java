import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class DP_16 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n == 0 || n % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		int[] dp = new int[n+1];
		
		dp[0] = 1;
		dp[2] = 3;
		
		for(int i=4; i<n+1; i+=2) {
			dp[i] = dp[i-2]*dp[2];
			for(int j=0; j<=i-4; j+=2)
				dp[i] += dp[j]*2;
		}
		
		System.out.println(dp[n]);
	}

}
