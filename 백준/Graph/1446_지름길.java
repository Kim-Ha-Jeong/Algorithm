import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BOJ_1446 {
	static int[] dp;
	static HashMap<Integer, List<int[]>> shortCut;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int D = Integer.parseInt(s[1]);
		shortCut = new HashMap<>();
		dp = new int[D+1];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			int star = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int val = Integer.parseInt(s[2]);
			
			if(!shortCut.containsKey(end)) {
				shortCut.put(end, new ArrayList<>());
			}
			
			shortCut.get(end).add(new int[] {star, val});
		}
		
		for(int i=1; i<D+1; i++) {
			dp[i] = dp[i-1]+1;
			
			if(shortCut.containsKey(i)) {
				for(int[] root : shortCut.get(i)) {
					dp[i] = Math.min(dp[i], dp[root[0]]+root[1]);
				}
			}
		}
		
		bw.write(dp[D]+"");
		bw.flush();
		bw.close();
	}

}
