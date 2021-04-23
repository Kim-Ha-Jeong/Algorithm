import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
public class DP_EX_6 {
	static String N;
	static int cache[];
	static int max = 12345678;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		while(tc > 0) {
			N = br.readLine().trim();
			cache = new int[N.length()];
			
			Arrays.fill(cache, -1);
			
			bw.flush();
			bw.write(memorize(0)+"\n");
			tc--;
		}
		
		bw.close();
	}
	
	static int classify(int a, int b) {
		String M = N.substring(a, b+1);
		String s = "";
		
		for(int i=0; i<M.length(); i++)
			s += M.charAt(0);
		
		if(M.equals(s)) return 1;
		
		int x = M.charAt(0)-'0';
		int y = M.charAt(1)-'0';
		boolean progressive = true;
		for(int i=1; i<M.length(); i++) {
			int m = M.charAt(i-1)-'0';
			int l = M.charAt(i)-'0';
			
			if(l-m != y-x) {
				progressive = false;
				break;
			}
		}
		

		if(progressive && Math.abs(x-y) == 1)
			return 2;
		
		boolean alternative = true;
		for(int i=0; i<M.length(); i++) {
			if(M.charAt(i)-'0' != M.charAt(i%2)-'0') {
				alternative = false;
				break;
			}
		}
		
		if(alternative) return 4;
		if(progressive) return 5;
		
		return 10;
		
	}
	
	static int memorize(int begin) {
		if(begin == N.length()) return 0;
		
		int ret = cache[begin];
		if(ret != -1) return ret;
		ret = max;
		
		for(int L=3; L<=5; L++) {
			if(begin+L <= N.length())
				ret = Math.min(ret, memorize(begin+L) + classify(begin, begin+L-1));
		}
		
		return ret;
	}
}
