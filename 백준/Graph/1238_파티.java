import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1238 {
	static int n, m, x;;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		x = Integer.parseInt(s[2]);
		
		ArrayList<Node>[] list = new ArrayList[n+1];
		ArrayList<Node>[] r_list = new ArrayList[n+1];
		int[] dist = new int[n+1];
		int[] r_dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(r_dist, Integer.MAX_VALUE);
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Node>();
			r_list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int weight = Integer.parseInt(s[2]);
			
			list[start].add(new Node(end, weight));
			r_list[end].add(new Node(start, weight));
		}
		
		dijkstra(list,dist);
		dijkstra(r_list,r_dist);
		
		int ret = -1;
		for(int i=1; i<n+1; i++) {
			ret = Math.max(ret, dist[i]+r_dist[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(ret).append("");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(ArrayList<Node>[] list, int[] dist) {
		boolean[] visited = new boolean[n+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(x, 0));
		dist[x] = 0;
		
		while(!pq.isEmpty()) {
			int cur = pq.poll().num;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			for(Node next : list[cur]) {
				if(dist[next.num] > dist[cur] + next.weight) {
					dist[next.num] = dist[cur] + next.weight;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		Node(int num, int weight){
			this.num = num;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
