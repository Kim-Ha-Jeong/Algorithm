import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Imple_8 {
	 public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String pattern = br.readLine().trim();
		
		int starIdx = pattern.indexOf('*');
		String pFront = pattern.substring(0,starIdx);
		String pBack = pattern.substring(starIdx+1);
		
		for(int j=0; j<n; j++) {
			String file = br.readLine().trim();
			
			if(file.length() < pattern.length()-1) {
				System.out.println("NE");
				continue;
			}
			
			String fFront = file.substring(0, pFront.length());
			String fBack = file.substring(file.length()-pBack.length());
			
			if(pFront.equals(fFront) && pBack.equals(fBack))
				System.out.println("DA");
			else
				System.out.println("NE");
			
		}
	}

}
