import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Queue;

public class BOJ_1260 {
	static ArrayList<Integer>[] list;
	static boolean[] v;
	static int n, m;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		int start = Integer.parseInt(s[2]);
		list = new ArrayList[n+1];
		v = new boolean[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i=1; i<n+1; i++) {
			Collections.sort(list[i]);
		}
		
		dfs(start);
		sb.append("\n");
		Arrays.fill(v, false);
		bfs(start);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int start) {
		v[start] = true;
		
		sb.append(start+" ");
		
		for(int i : list[start]) {
			if(!v[i]) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		v[start] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			sb.append(cur+" ");
			
			for(int i: list[cur]) {
				if(!v[i]) {
					v[i] = true;
					q.add(i);
				}
			}
		}
	}

}
