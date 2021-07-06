import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_16 {
	static int[] pi;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine();
			
			if(s.equals("."))
				return;
			
			int len = s.length();
			pi = new int[len];
			
			int last = pi[len-1];
			
			if(len%(len-last) != 0)
				System.out.println(1);
			else
				System.out.println(len / (len - last));
		}
	}
	
	static int[] getPi(String pattern) {
		char[] p = pattern.toCharArray();
		
		int j = 0;
		for(int i=1; i<p.length; i++) {
			while(j>0 && p[i] != p[j]) {
				j = pi[j-1];
			}
			
			if(p[i] == p[j]) {
				pi[i] = ++j;
			}
		}
		
		return pi;
	}

}
