import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_17 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		int max = -1;
		
		for(int i=0; i<len; i++) {
			String part = s.substring(i, len);
			int[] fail = new int[part.length()];
			fail = getPi(part); 
			for(int j=0; j<fail.length; j++) {
				max = Math.max(max, fail[j]);
			}
		}
		
		System.out.println(max);
	}
	
	static int[] getPi(String s) {
		int len = s.length();
		int[] pi = new int[len];
		int j = 0;
		
		for(int i=1; i<len; i++) {
			while(j>0 && s.charAt(i) != s.charAt(j)) {
				j = pi[j-1];
			}
			
			if(s.charAt(i) == s.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

}
