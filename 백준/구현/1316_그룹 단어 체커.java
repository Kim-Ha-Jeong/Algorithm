import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_7 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] word = new String[n];
		boolean[] alphabet;
		
		for(int i=0; i<n; i++) {
			word[i] = br.readLine().trim();
		}
		
		int count = 0;
		
		for(int i=0; i<n; i++) {
			alphabet = new boolean[26];
			for(int j=1; j<word[i].length(); j++) {
				if(word[i].charAt(j-1) != word[i].charAt(j)) {
					if(alphabet[word[i].charAt(j) - 'a']) {
						count++;
						break;
					}
					alphabet[word[i].charAt(j-1)-'a'] = true;
				}
			}
		}
		
		System.out.println(n-count);
	}

}
