import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_18 {
	static int[] pi;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		
		pi = new int[P.length()];
		
		getPi(P);
		KMP(S, P);
		
		System.out.println(count);
	}
	
	static void getPi(String pattern) {
		int j = 0;
		for(int i=1; i<pattern.length(); i++) {
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
	}
	
	static void KMP(String str, String pattern) {
		int j = 0;
		for(int i=0; i<str.length(); i++) {
			while(j>0 && str.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(str.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1) {
					count = 1;
					break;
				} else {
					j++;
				}
			}
		}
	}

}
