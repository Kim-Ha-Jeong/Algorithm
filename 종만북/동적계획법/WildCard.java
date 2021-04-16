import java.util.*;

public class DP_EX_2 {
	static int cache[][] = new int[101][101];
	static String W,S;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc > 0) {
			W = sc.next();
			int n = sc.nextInt();
			ArrayList<String> ret = new ArrayList<String>();
			
			for(int[] arr: cache) 
				Arrays.fill(arr, -1);
			
			for(int i=0; i<n; i++) {
				S = sc.next();
				if(match(0,0) == 1)
					ret.add(new String(S));
			}
			
			Collections.sort(ret);
			
			for(int i=0; i<ret.size(); i++)
				System.out.println(ret.get(i));
		
		}
	}
	
	static int match(int w, int s) {
			int ret = cache[w][s];
			if(ret != -1) return ret;
			
			if(s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) 
				return ret = match(w+1,s+1);
			
			if(w == W.length()) return ret = (s == S.length()) ? 1:0;
			
			if(W.charAt(w) == '*') {
				if(match(w+1, s) == 1 || (s < S.length() && match(w,s+1) == 1))
					return ret = 1;
			}
			
			return ret = 0;
	}

}
