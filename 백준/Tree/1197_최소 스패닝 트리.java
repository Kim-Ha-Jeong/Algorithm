import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
public class MST_1 {
	static int[] parent;
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		
		int count = 0;
		int V = Integer.parseInt(s[0]);
		int E = Integer.parseInt(s[1]);
		parent = new int[V+1];
		pq = new PriorityQueue<Edge>();
		
		for(int i=0; i<V+1; i++)
			parent[i] = i;
		
		for(int i=0; i<E; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			pq.offer(new Edge(start,end,cost));
		}
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(find(e.start) == find(e.end)) continue;
			else {
				union(e.start, e.end);
				count+=e.cost;
			}
		}
		
		System.out.println(count);
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
