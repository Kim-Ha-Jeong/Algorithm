import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
public class BOJ_14938 {
	static ArrayList<Node>[] list;
	static int[] dist;
	static int[] item;
	static int n,m,r;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		r = Integer.parseInt(s[2]);
		
		item = new int[n+1];
		dist = new int[n+1];
		list = new ArrayList[n+1];
		
		s = br.readLine().split(" ");
		for(int i=1; i<n+1; i++) {
			item[i] = Integer.parseInt(s[i-1]);
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<r; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int weight = Integer.parseInt(s[2]);
			list[start].add(new Node(end,weight));
			list[end].add(new Node(start, weight));
		}
		
		int ans = -1;
		for(int i=1; i<n+1; i++) {
			dijkstra(i);
			ans = Math.max(getMaxItems(dist), ans);
		}
		
		StringBuilder sb = new StringBuilder();
		bw.write(sb.append(ans).toString());
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		boolean[] visited = new boolean[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;
			
			if(visited[num]) continue;
			visited[num] = true;
			
			for(Node next : list[num]) {
				if(dist[next.num] > dist[num]+next.val) {
					dist[next.num] = dist[num]+next.val;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}
	}
	
	static int getMaxItems(int[] dist) {
		int ret = 0;
		for(int i=1; i<n+1; i++) {
			if(dist[i] <= m) {
				ret += item[i];
			}
		}
		
		return ret;
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int val;
		Node(int num, int val) {
			this.num = num;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}

}
