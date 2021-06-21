import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
public class DP_14 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		int[][] dp = new int[n][3];
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=1; i<n; i++) {
			cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]);
			cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]);
			cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]);
			
		}
		
		bw.write(Math.min(Math.min(cost[n-1][0], cost[n-1][1]), cost[n-1][2])+"");
		bw.flush();
		bw.close();
	}

}
