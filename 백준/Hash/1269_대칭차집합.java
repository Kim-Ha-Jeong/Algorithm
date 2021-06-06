import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Hash_5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		HashSet<Integer> A = new HashSet<>();
		
		s = br.readLine().split(" ");
		
		for(int i=0; i<n; i++)
			A.add(Integer.parseInt(s[i]));
		
		int[] B = new int[m+1];
		s = br.readLine().split(" ");
		
		for(int i=0; i<m; i++)
			B[i] = Integer.parseInt(s[i]);
		
		int count = 0;
		for(int i=0; i<m; i++) {
			if(A.contains(B[i]))
				count++;
		}
		
		System.out.println(n+m-2*count);
	}

}
