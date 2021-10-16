import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_11779 {
	static ArrayList<Node>[] list;
	static int[] arr;
	static int s,e, n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		arr = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			String[] str = br.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			int val = Integer.parseInt(str[2]);			
			list[start].add(new Node(end,val));
		}
		
		String[] str = br.readLine().split(" ");
		s = Integer.parseInt(str[0]);
		e = Integer.parseInt(str[1]);
		
		int dist = dijkstra(s);
		
		int cnt = 0;
		ArrayList<Integer> ans = new ArrayList<>();
		int tmp = e;
		while(tmp != 0) {
			cnt++;
			ans.add(tmp);
			tmp = arr[tmp];
		}
		
		sb.append(dist).append("\n");
		sb.append(cnt).append("\n");
		for(int i=ans.size()-1; i>=0; i--) {
			sb.append(ans.get(i)).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] v = new boolean[n+1];
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;
			
			if(v[num]) continue;
			v[num] = true;
			
			for(Node next : list[num]) {
				if(dist[next.num] > dist[num] + next.val ) {
					dist[next.num] = dist[num] + next.val;
					pq.add(new Node(next.num, dist[next.num]));
					arr[next.num] = num;
				}
			}
			
		}
		
		return dist[e];
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int val;
		Node(int num, int val){
			this.num = num;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
				return this.val - o.val;
		}
	}
}
