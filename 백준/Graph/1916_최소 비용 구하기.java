import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1916 {
	static ArrayList<Node>[] list;
	static int[] dp;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		dp = new int[n+1];
		visited = new boolean[n+1];
		
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		String[] s;
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			list[Integer.parseInt(s[0])].add(new Node(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
		
		s = br.readLine().split(" ");
		int start = Integer.parseInt(s[0]);
		int dest = Integer.parseInt(s[1]);
		
		dijkstra(start);
		
		bw.write(dp[dest]+"");
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		pq.add(new Node(start, 0));
		dp[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(visited[node.to]) continue;
			
			visited[node.to] = true;
			
			for(Node next : list[node.to]) {
				if(dp[next.to] >= dp[node.to] + next.val) {
					dp[next.to] = dp[node.to]+next.val;
					pq.add(new Node(next.to, dp[next.to]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int to;
		int val;
		Node(int to, int val){
			this.to = to;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}

}
