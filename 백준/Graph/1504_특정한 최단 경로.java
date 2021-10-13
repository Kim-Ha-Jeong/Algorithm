import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1504 {
	static ArrayList<Node>[] list;
	static int[] dist;
	static int n,m;
	static final int INF = 200000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		list = new ArrayList[n+1];
		dist = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int weight = Integer.parseInt(s[2]);
			
			list[start].add(new Node(end, weight));
			list[end].add(new Node(start, weight));
		}
		
		s = br.readLine().split(" ");
		int v1 = Integer.parseInt(s[0]);
		int v2 = Integer.parseInt(s[1]);
		
		int ret1 = 0;
		ret1 += dijkstra(1, v1);
		ret1 += dijkstra(v1, v2);
		ret1 += dijkstra(v2, n);
		
		int ret2 = 0;
		ret2 += dijkstra(1, v2);
		ret2 += dijkstra(v2, v1);
		ret2 += dijkstra(v1, n);
		
		int ans = (ret1 >= INF && ret2 >= INF) ? -1 : Math.min(ret1, ret2);
		
		StringBuilder sb = new StringBuilder();
		bw.write(sb.append(ans).append("").toString());
		bw.flush();
		bw.close();
	}
	
	static int dijkstra(int start, int end) {
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n+1];
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.end]) continue;
			
			visited[cur.end] = true;
			
			for(Node next : list[cur.end]) {
				if(!visited[next.end] && dist[next.end] > dist[cur.end] + next.weight) {
					dist[next.end] = dist[cur.end] + next.weight;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
		
		return dist[end];
	}
	
	static class Node implements Comparable<Node>{
		int end;
		int weight;
		
		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

}
