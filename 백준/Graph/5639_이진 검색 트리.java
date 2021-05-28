import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Greedy_7 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int count[] = new int[2];
		
		count[s.charAt(0)-'0']++;
		for(int i=1; i<s.length(); i++) {
			char a = s.charAt(i);
			char b = s.charAt(i-1);
			if(a!=b) 
				count[a-'0']++;
		}
		
		System.out.println(Math.min(count[0], count[1]));
		
	}

}
