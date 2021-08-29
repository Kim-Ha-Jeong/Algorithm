import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1783 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int ret = -1;
		
		if(n == 1) ret = 1;
		else if(n == 2) ret = Math.min(4, (m+1)/2);
		else {
			if(m < 7) ret = Math.min(4, m);
			else ret = m - 2;
		}
		
		System.out.println(ret);
	}

}
