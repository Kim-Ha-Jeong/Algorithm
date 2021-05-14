import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Greedy_11 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		long road[] = new long[n - 1];
		long city[] = new long[n];

		String s[] = br.readLine().split(" ");
		for (int i = 0; i < n - 1; i++)
			road[i] = Long.parseLong(s[i]);

		s = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			city[i] = Long.parseLong(s[i]);

		long ret = city[0] * road[0];
		long min = city[0];

		for (int i = 1; i < n-1; i++) {
			min = Math.min(min, city[i]);
			ret += min * road[i];
		}
		
		System.out.println(ret);
	}

}
