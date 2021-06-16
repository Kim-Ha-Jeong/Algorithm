import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Bruteforce_9 {
	static List<Integer>[] relation;
	static boolean[] visited;
	static int a, b, count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		String[] s = br.readLine().split(" ");
		a = Integer.parseInt(s[0]);
		b = Integer.parseInt(s[1]);

		int m = Integer.parseInt(br.readLine());
		relation = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		for (int i = 1; i < n + 1; i++)
			relation[i] = new ArrayList<Integer>();

		for (int i = 0; i < m; i++) {
			s = br.readLine().split(" ");
			int parent = Integer.parseInt(s[0]);
			int child = Integer.parseInt(s[1]);
			relation[parent].add(child);
			relation[child].add(parent);
		}
		dfs(a, 0);
		visited[a] = true;

		System.out.println(count == 0 ? -1 : count);
	}

	static void dfs(int x, int cnt) {
		if (x == b) {
			count = cnt;
			return;
		}

		for (int target : relation[x]) {
			if (!visited[target]) {
				visited[target] = true;
				dfs(target, cnt + 1);
			}
		}

	}

}
