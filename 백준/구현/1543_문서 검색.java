import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Imple_9 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String document = br.readLine().trim();
		String word = br.readLine().trim();
		
		int wLength = word.length();
		int idx = 0;
		int count = 0;
		
		while(true) {
			
			if(idx+wLength > document.length())
				break;
			
			String sub = document.substring(idx, idx+wLength);
			
			if(sub.equals(word)) {
				idx += wLength;
				count++;
			} else {
				idx += 1;
			}
		}
		
		System.out.println(count);
	}

}
