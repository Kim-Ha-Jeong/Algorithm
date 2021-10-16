import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class BOJ_18352 {
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> ans;
	static int n,m,k,x;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		x = Integer.parseInt(s[3]);
		
		list = new ArrayList[n+1];
		ans = new ArrayList<>();
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			list[a].add(b);
		}
		
		bfs(x);
		
		Collections.sort(ans);
		if(ans.size() == 0) {
			sb.append(-1);
		} else {
			for(int i : ans) {
				sb.append(i).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		
		q.add(new Node(start, 0));
		visited[start] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;
			
			if(cur.val == k) {
				ans.add(cur.num);
			}
			
			for(int next : list[num]) {
				if(!visited[next]) {
					visited[next] = true;
					q.add(new Node(next, cur.val+1));
				}
			}
		}
	}
	
	
	static class Node{
		int num;
		int val;
		Node(int num, int val){
			this.num = num;
			this.val = val;
		}
	}
}
