package study;

import java.io.*;
import java.util.*;

public class DfsBfs {
	static int n, m, v;
	static ArrayList<Integer>[] list;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		v = Integer.parseInt(s[2]);

		list = new ArrayList[n+1];
		visited = new boolean[n + 1];
		
		for(int i=1; i<n+1; i++)
			list[i] = new ArrayList<Integer>();

		for (int i = 0; i < m; i++) {
			String str[] = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i=1; i<n+1; i++)
			Collections.sort(list[i]);

		dfs(v);
		System.out.println();
		bfs();
	}

	public static void dfs(int vertex) {
		visited[vertex] = true;

		System.out.print(vertex + " ");

		for (int i : list[vertex]) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs() {
		for (int i = 1; i < n + 1; i++)
			visited[i] = false;

		Queue<Integer> q = new LinkedList<>();
		q.add(v);

		while (!q.isEmpty()) {
			int vertex = q.poll();
			visited[vertex] = true;

			for (int i : list[vertex]) {
				if (!visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}

			System.out.print(vertex + " ");
		}
	}
}
