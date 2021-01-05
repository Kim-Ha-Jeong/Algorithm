import java.util.*;
import java.io.*;

public class BOJ1697 {
	static int min = Integer.MAX_VALUE;
	static int arr[];
	static int n, k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");

		n = Integer.parseInt(s[0]);
		k = Integer.parseInt(s[1]);
		arr = new int[100001];

		if(n == k)
			System.out.println(0);
		else
			bfs();

	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();

		q.add(n);

		while (!q.isEmpty()) {
			int v = q.poll();
			
			if (v == k) {
				System.out.println(arr[v]);
				return;
			}

			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0)
					next = v * 2;
				else if (i == 1)
					next = v + 1;
				else
					next = v - 1;


				if (next >= 0 && next <= 100000 && arr[next] == 0) {
					q.add(next);
					arr[next] = arr[v] + 1;
				}
			}
		}

	}
}
