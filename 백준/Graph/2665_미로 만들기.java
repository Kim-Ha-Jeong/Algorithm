import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
public class BOJ_2665 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		visited = new boolean[n][n];
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		bw.write(dijkstra(0,0)+"");
		bw.flush();
		bw.close();
		
	}
	
	static int dijkstra(int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(x,y,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.x == n-1 && cur.y == n-1)
				return cur.val;
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					int tmp = arr[nx][ny] == 0 ? 1 : 0;
					pq.add(new Node(nx,ny,cur.val + tmp));
				}
			}
		}
		
		return 0;
	}
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int val;
		Node(int x, int y, int val){
			this.x = x;
			this.y = y;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}

}
