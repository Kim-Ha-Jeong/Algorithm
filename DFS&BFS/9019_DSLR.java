import java.util.*;
import java.io.*;

public class BOJ_9019 {
	static int arr[][];
	static boolean visited[] = new boolean[10000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		arr = new int[t][2];

		for (int i = 0; i < t; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < 2; j++)
				arr[i][j] = Integer.parseInt(s[j]);
		}

		for (int i = 0; i < t; i++) {
			bfs(arr[i][0], arr[i][1]);
			Arrays.fill(visited, false);
		}
	}

	public static void bfs(int a, int b) {
		Queue<StrInt> q = new LinkedList<>();
		q.add(new StrInt(a, ""));
		visited[a] = true;

		while (!q.isEmpty()) {
			String s[] = new String[4];
			StrInt now = q.poll();
			int n = now.n;
			int D = (n * 2) % 10000;
			int S = (n == 0) ? 9999 : n - 1;
			int L = (n % 1000) * 10 + n / 1000;
			int R = (n % 10) * 1000 + n / 10;

			if (now.n == b) {
				System.out.println(now.s);
				return;
			}

			if (!visited[D]) {
				visited[D] = true;
				s[0] = now.s + "D";
				q.add(new StrInt(D, s[0]));
			}

			if (!visited[S]) {
				visited[S] = true;
				s[1] = now.s + "S";
				q.add(new StrInt(S, s[1]));
			}

			if (!visited[L]) {
				visited[L] = true;
				s[2] = now.s + "L";
				q.add(new StrInt(L, s[2]));
			}

			if (!visited[R]) {
				visited[R] = true;
				s[3] = now.s + "R";
				q.add(new StrInt(R, s[3]));
			}

		}
	}

}

class StrInt {
	int n;
	String s;

	StrInt(int n, String s) {
		this.n = n;
		this.s = s;
	}
}
