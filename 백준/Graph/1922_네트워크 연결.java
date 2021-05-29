import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;
public class Graph_8 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		for(int i=1; i<n+1; i++)
			parent[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i=0; i<m; i++) {
			String[] s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			
			if(start == end)
				continue;
			
			pq.add(new Edge(start, end, cost));
		}
		
		int ret = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(find(e.start) == find(e.end))
				continue;
			else {
				union(e.start, e.end);
				ret += e.cost;
			}
		}
		
		System.out.println(ret);
	}
	
	static int find(int e) {
		if(parent[e] == e) return e;
		return parent[e] = find(parent[e]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) parent[rootB] = rootA;
	}
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		
		Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

}
