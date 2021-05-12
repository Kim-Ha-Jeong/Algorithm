import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Greedy_10 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = new String[3];
		int count = 1;
		
		while (true) {
			s = br.readLine().split(" ");

			int L = Integer.parseInt(s[0]);
			int P = Integer.parseInt(s[1]);
			int V = Integer.parseInt(s[2]);

			if(L == 0 && P == 0 && V == 0)
				break;
			
			int sum = 0, rest = 0;
			int share = V / P;
	
			sum += L * share;
			rest = V % P;

			if (rest <= L)
				sum += rest;
			else
				sum += L;
			
			System.out.println("Case "+count+": "+sum);
			count++;
		}
	}

}
