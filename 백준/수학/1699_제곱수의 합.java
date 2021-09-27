import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class BOJ_1699 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n<4) {
			System.out.println(n);
			return;
		}
		
		int[] dp = new int[n+1];
		
		for(int i=0; i<4; i++) {
			dp[i] = i;
		}
		
		for(int i=4; i<n+1; i++) {
			dp[i] = 100001;
			int sub = (int)Math.floor(Math.sqrt(i));
			
			for(int j=sub; j>=0; j--) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
			}
		}
		
		bw.write(dp[n]+"");
		bw.flush();
		bw.close();
	}

}
