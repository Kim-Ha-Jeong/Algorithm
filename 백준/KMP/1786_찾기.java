import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Imple_15 {
	static String T, P;
	static int[] pi;
	static int count = 0;
	static ArrayList<Integer> ret = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = br.readLine();
		P = br.readLine();
		pi = new int[P.length()];
		
		getPi();
		KMP();
		
		System.out.println(count);
		
		for(int i : ret) {
			System.out.println(i);
		}
	}

	static void getPi() {
		int j = 0;
		for(int i=1; i<P.length(); i++) {
			while(j>0 && P.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			
			if(P.charAt(i) == P.charAt(j)) {
				pi[i] = ++j;
			}
		}
	}
	
	static void KMP() {
		int j = 0;
		for(int i=0; i<T.length(); i++) {
			while(j>0 && T.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			
			if(T.charAt(i) == P.charAt(j)) {
				if(j == P.length()-1) {
					count++;
					ret.add(i-P.length()+2);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}
}
