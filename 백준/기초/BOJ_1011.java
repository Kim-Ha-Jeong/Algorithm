import java.io.*;

public class BOJ_1011 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int start[] = new int[t];
		int end[] = new int[t];

		for (int i = 0; i < t; i++) {
			String s[] = br.readLine().split(" ");
			start[i] = Integer.parseInt(s[0]);
			end[i] = Integer.parseInt(s[1]);
		}

		for (int i = 0; i < t; i++) {
			int cnt = 0;
			int distance = end[i] - start[i];
			int max = (int) Math.sqrt(distance);

			if (max == Math.sqrt(distance))
				cnt = 2 * max - 1;
			else if (distance <= max * max + max)
				cnt = 2 * max;
			else
				cnt = 2 * max + 1;

			System.out.println(cnt);

		}

	}

}
