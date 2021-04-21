import java.io.BufferedReader;
import java.io.InputStreamReader;
public class DP_EX_4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = -1;
		
		int n = Integer.parseInt(br.readLine());
		String s[] = br.readLine().split(" ");
		
		int lis[] = new int[n];
		int dp[] = new int[n];
		
		for(int i=0; i<n; i++)
			lis[i] = Integer.parseInt(s[i]);
		
		for(int i=0; i<n; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(lis[j]<lis[i] && dp[i] <= dp[j])
					dp[i] = dp[j] + 1;
			}
		}
		
		for(int i=0; i<n; i++)
			max = Math.max(max, dp[i]);
		
		System.out.println(max);
	}

}
