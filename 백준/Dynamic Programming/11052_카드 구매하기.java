import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class DP_17 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		int[] price = new int[n+1];
		int[] dp = new int[n+1];
		
		String[] s = br.readLine().split(" ");
		
		for(int i=1; i<n+1; i++) {
			price[i] = Integer.parseInt(s[i-1]);
		}
		
		dp[1] = price[1];
		
		for(int i=2; i<n+1; i++) {
			for(int j=1; j<=i/2; j++) {
				dp[i] = Math.max(dp[i], dp[j]+dp[i-j]);
			}
			dp[i] = Math.max(dp[i], price[i]);
		}
		
		bw.write(String.valueOf(dp[n]));
		bw.flush();
		bw.close();
	}

}
