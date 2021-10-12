import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
public class BOJ_1261 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n, m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		visited = new boolean[m][n];
		arr = new int[m][n];
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split("");
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
		
		pq.add(new Node(x,y,arr[x][y]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.x == m-1 && cur.y == n-1)
				return cur.val;
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					pq.add(new Node(nx,ny,cur.val + arr[nx][ny]));
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
