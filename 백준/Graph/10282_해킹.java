import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_10282 {
	static ArrayList<Node>[] list;
	static int[] dist;
	static int n;
	static int computer;
	static int time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			int d = Integer.parseInt(str[1]);
			int c = Integer.parseInt(str[2]);
			list = new ArrayList[n+1];
			dist = new int[n+1];
			computer = n;
			time = 0;
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<d; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				int s = Integer.parseInt(str[2]);
				list[b].add(new Node(a,s));
			}
			
			dijkstra(c);
			sb.append(computer).append(" ").append(time).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n+1];
		dist[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.end;
			
			if(visited[num]) continue;
			visited[num] = true;
			
			for(Node next : list[num]) {
				if(dist[next.end] > dist[num]+next.val) {
					dist[next.end] = dist[num]+next.val;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
			
		}
		
		for(int i=1; i<n+1; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				computer--;
			} else {
				time = Math.max(time, dist[i]);
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int end;
		int val;
		Node(int end, int val){
			this.end = end;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}
}
