import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;
public class Graph_5 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int count = 0;
		int max = -1;
		parent = new int[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i=1; i<N+1; i++)
			parent[i] = i;
		
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			pq.add(new Edge(start,end,cost));
		}
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(find(e.start) == find(e.end))
				continue;
			else {
				union(e.start, e.end);
				count += e.cost;
				max = Math.max(max, e.cost);
			}
		}
		
		System.out.println(count-max);
		
	}
	
	static int find(int e) {
		if(e == parent[e]) return e;
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
		
		Edge(int start, int end, int cost){
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
