import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
public class BOJ_5557 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		long[][] dp = new long[n][21];
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		
		dp[0][arr[0]] = 1;
		
		for(int i=1; i<n; i++) {
			for(int j=0; j<21; j++) {
				if(dp[i-1][j] == 0) continue;
				
				int add = j + arr[i];
				int sub = j - arr[i];
				
				if(add <= 20) {
					dp[i][add] += dp[i-1][j];
				}
				
				if(sub >= 0) {
					dp[i][sub] += dp[i-1][j];
				}
			}
		}
		
		bw.write(dp[n-2][arr[n-1]]+"");
		bw.flush();
		bw.close();
	}

}
